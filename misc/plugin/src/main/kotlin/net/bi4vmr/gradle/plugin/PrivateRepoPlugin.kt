package net.bi4vmr.gradle.plugin

import net.bi4vmr.gradle.data.MavenRepos
import net.bi4vmr.gradle.entity.MavenRepo
import net.bi4vmr.gradle.util.NetUtil
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.io.File

/**
 * Maven私有仓库插件。
 *
 * 自动为子模块添加常用的私有仓库。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class PrivateRepoPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        // 如果网络测试结果不存在，则先进行测试；否则根据测试结果选择仓库。
        val testResult = File("${target.rootDir}${File.separatorChar}build${File.separatorChar}net-test.txt")
        if (!testResult.exists()) {
            if (NetUtil.scanByTCP("172.16.5.1", 8081)) {
                println("Current host is in private network, add LAN repositories.")
                testResult.writeText(MavenRepos.PRIVATE_LAN.name)
            } else if (NetUtil.scanByTCP("127.0.0.1", 8081)) {
                println("Current host is not in private network, add LOCAL repositories.")
                testResult.writeText(MavenRepos.PRIVATE_LOCAL.name)
            } else {
                println("Current host is not in private network, add MavenLocal repository.")
                testResult.writeText("MavenLocal")
            }

            addRepos(target.repositories, testResult.readText())
        } else {
            addRepos(target.repositories, testResult.readText())
        }
    }

    private fun addRepos(handler: RepositoryHandler, info: String) {

        fun addRepo(repo: MavenRepo) {
            with(handler) {
                repo.let {
                    maven {
                        setUrl(it.url)
                        isAllowInsecureProtocol = true
                    }
                }
            }
        }

        when (info) {
            MavenRepos.PRIVATE_LAN.name -> {
                addRepo(MavenRepos.PRIVATE_LAN)
            }

            MavenRepos.PRIVATE_LOCAL.name -> {
                addRepo(MavenRepos.PRIVATE_LOCAL)
            }

            else -> {
                handler.mavenCentral()
            }
        }
    }
}
