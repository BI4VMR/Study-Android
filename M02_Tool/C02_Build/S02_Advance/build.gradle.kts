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
        applicationId = "net.bi4vmr.study.tool.build.advance"
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

            // 向BuildConfig类添加字段
            buildConfigField("String", "SERVER_NAME", "\"http://test.example.net/\"")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("AOSP")

            // 向BuildConfig类添加字段
            buildConfigField("String", "SERVER_NAME", "\"http://prod.example.net/\"")
        }
    }

    sourceSets {
        getByName("main") {
            kotlin.srcDir("src/main/kotlin")
        }
    }

    compileOptions {
        // 指定Java源码编译目标版本
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // 打包APK时的选项
    packagingOptions {
        // 不添加任何配置时，默认不过滤任何文件。

        // 打包时排除所有x86架构的库文件
        jniLibs.excludes.add("lib/x86/*.so")

        // 如果多个模块的LICENSE文件路径相同，则将它们合并为同一个文件。
        resources.merges.add("**/LICENSE.txt")
    }

    buildFeatures {
        viewBinding = true

        // 此配置项可以禁止本模块生成BuildConfig文件
        // buildConfig = false
        buildConfig = true
    }
}

dependencies {
    implementation(libAndroid.bundles.appBaseKT)
}
