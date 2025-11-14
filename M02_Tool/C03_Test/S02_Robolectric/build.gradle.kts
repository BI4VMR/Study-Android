@file:Suppress("UnstableApiUsage")

val versionMinSDK: Int = agp.versions.minSdk.get().toInt()
val versionCompileSDK: Int = agp.versions.compileSdk.get().toInt()
val versionTargetSDK: Int = agp.versions.targetSdk.get().toInt()
val versionModuleCode: Int = agp.versions.moduleCode.get().toInt()
val versionModuleName: String = agp.versions.moduleName.get()

plugins {
    alias(libAndroid.plugins.application)
    alias(libAndroid.plugins.kotlin)
}

android {
    namespace = "net.bi4vmr.study"
    compileSdk = versionCompileSDK

    defaultConfig {
        applicationId = "net.bi4vmr.study.tool.test.robolectric"
        minSdk = versionMinSDK
        targetSdk = versionTargetSDK
        versionCode = versionModuleCode
        versionName = versionModuleName
    }

    signingConfigs {
        create("AOSP") {
            storeFile =
                file("${rootDir.absolutePath}${File.separator}misc${File.separator}keystore${File.separator}AOSP.keystore")
            storePassword = "AOSPSystem"
            keyAlias = "AOSPSystem"
            keyPassword = "AOSPSystem"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("AOSP")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("AOSP")
        }
    }

    compileOptions {
        // 指定Java源码编译目标版本
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        // 指定Kotlin源码编译目标版本
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests {
            // 为单元测试添加Android资源
            isIncludeAndroidResources = true
        }

        unitTests.all {
            // Robolectric：启用日志输出
            it.systemProperty("robolectric.logging.enabled", true)

            // Robolectric：指定模拟API的JAR包下载仓库
            it.systemProperty("robolectric.dependency.repo.id", "Mirror_Tencent")
            it.systemProperty(
                "robolectric.dependency.repo.url",
                "https://mirrors.cloud.tencent.com/nexus/repository/maven-public/"
            )
            // it.systemProperty("robolectric.dependency.repo.username", "username")
            // it.systemProperty("robolectric.dependency.repo.password", "password")

            // Robolectric：指定代理服务器（需要Robolectric v4.9.1或更高版本）
            // it.systemProperty("robolectric.dependency.proxy.host", System.getenv("ROBOLECTRIC_PROXY_HOST"))
            // it.systemProperty("robolectric.dependency.proxy.port", System.getenv("ROBOLECTRIC_PROXY_PORT"))

            // Robolectric：启用离线模式（需要预先下载所需的JAR包）
            // it.systemProperty("robolectric.offline", true)
            // it.systemProperty("robolectric.dependency.dir", "$rootDir/robolectric")
        }
    }
}

dependencies {
    implementation(libAndroid.bundles.appBaseKT)

    testImplementation(libJava.junit4)
    // 声明Robolectric依赖项
    testImplementation(libAndroid.robolectric.core)
}
