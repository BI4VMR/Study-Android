@file:Suppress("UnstableApiUsage")

val versionMinSDK: Int = agp.versions.minSdk.get().toInt()
val versionCompileSDK: Int = agp.versions.compileSdk.get().toInt()
val versionTargetSDK: Int = agp.versions.targetSdk.get().toInt()
val versionModuleCode: Int = agp.versions.moduleCode.get().toInt()
val versionModuleName: String = agp.versions.moduleName.get()
val versionComposeCompiler: String = libAndroid.versions.compose.compiler.get()

plugins {
    alias(libAndroid.plugins.application)
    alias(libAndroid.plugins.kotlin)
}

android {
    namespace = "net.bi4vmr.study"
    compileSdk = versionCompileSDK

    defaultConfig {
        applicationId = "net.bi4vmr.study.ui.compose.base"
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

    sourceSets {
        getByName("main") {
            java {
                java.srcDir("src/main/kotlin")
            }
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
        compose = true
    }

    composeOptions {
        /**
         * Jetpack Compose注解处理器版本，需要与Kotlin版本相匹配，对应关系可在Android官方网页查询：
         *
         * https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility
         */
        kotlinCompilerExtensionVersion = versionComposeCompiler
    }
}

dependencies {
    implementation(libAndroid.bundles.appBaseKT)

    // Jetpack Compose BOM
    implementation(platform(libAndroid.compose.bom))
    // Jetpack Compose 核心组件
    implementation(libAndroid.bundles.compose.core)
}
