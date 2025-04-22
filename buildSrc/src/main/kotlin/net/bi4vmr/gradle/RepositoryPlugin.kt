package net.bi4vmr.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler


/**
 * TODO 添加简述。
 *
 * TODO 添加详情。
 *
 * @author bi4vmr@outlook.com
 * @since 1.0.0
 */
class RepositoryPlugin : Plugin<Project> {

    companion object {
        private val repo: MutableList<MavenRepo> = mutableListOf()

        /**
         * 预置公共仓库，私有仓库请在```maven_config.json```中添加
         */
        val PRE_REPO = mutableListOf(
            MavenRepo(
                alias = "JITPACK",
                repoUrl = "https://jitpack.io"
            ),
            MavenRepo(
                alias = "ALIYUN_GOOGLE",
                repoUrl = "https://maven.aliyun.com/repository/google"
            ),
            MavenRepo(
                alias = "ALIYUN_CENTRAL",
                repoUrl = "https://maven.aliyun.com/repository/central"
            ),
            MavenRepo(
                alias = "ALIYUN_PUBLIC",
                repoUrl = "https://maven.aliyun.com/repository/public"
            )
        )
    }

    data class MavenRepo(
        // 别名 short name
        val alias: String,
        // url
        val repoUrl: String,
        // 用户名
        val repoUserName: String = "",
        // 密码
        val repoPassword: String = ""
    ) {
        override fun toString(): String {
            val maskedPassword = if (repoPassword.isNotEmpty()) "*".repeat(8) else ""
            return "MavenRepo(alias='$alias', repoUrl='$repoUrl', repoUserName='$repoUserName', repoPassword='$maskedPassword')"
        }
    }

    private fun addRepos(handler: RepositoryHandler, showLog: Boolean) {
        with(handler) {
            repo.addAll(PRE_REPO)
            repo.filter {
                it.repoUrl.isNotEmpty()
            }.forEach {
                maven {
                    isAllowInsecureProtocol = true
                    if (it.repoPassword.isNotEmpty() && it.repoUserName.isNotEmpty()) {
                        credentials {
                            username = it.repoUserName
                            password = it.repoPassword
                        }
                    }
                    setUrl(it.repoUrl)
                }
            }
            google()
            mavenCentral()
        }
    }

    override fun apply(target: Project) {
        println("apply-----[${target}]")
        // target.dependencyResolutionManagement.repositories.forEach { repository ->
        //     println("----- ${repository.name}")
        //     if (repository is MavenArtifactRepository) {
        //         println("Maven Repository - Name: ${repository.name}, URL: ${repository.url}")
        //         println("Credentials - Username: ${repository.credentials.username}, Password: ${"*".repeat(8)}")
        //     }else{
        //         println("it -> ${repository::class.simpleName}")
        //     }
        // }
        repo.addAll(PRE_REPO)
        addRepos(target.repositories, false)
    }
}
