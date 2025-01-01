plugins {
    alias(libAndroid.plugins.application)
    alias(libAndroid.plugins.kotlin)
    alias(libKotlin.plugins.ksp)
}

val versionMinSDK: Int = agp.versions.minSdk.get().toInt()
val versionCompileSDK: Int = agp.versions.compileSdk.get().toInt()
val versionTargetSDK: Int = agp.versions.targetSdk.get().toInt()
val versionModuleCode: Int = agp.versions.moduleCode.get().toInt()
val versionModuleName: String = agp.versions.moduleName.get()

@Suppress("UnstableApiUsage")
android {
    namespace = "net.bi4vmr.study"
    compileSdk = versionCompileSDK

    defaultConfig {
        applicationId = "net.bi4vmr.study.storage.sql.room"
        minSdk = versionMinSDK
        targetSdk = versionTargetSDK
        versionCode = versionModuleCode
        versionName = versionModuleName

        javaCompileOptions {
            ksp {
                // 指定Room导出调试信息的路径
                arg("room.schemaLocation", "$projectDir/RoomSchema")
            }
        }
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
}

dependencies {
    implementation(libAndroid.bundles.appBaseKT)
    // Room核心
    implementation(libAndroid.room.runtime)
    // Room Kotlin语言扩展
    implementation(libAndroid.room.ktx)

    // Web调试工具
    debugImplementation(libAndroid.debugDB)

    // Room注解处理器(Java)
    // annotationProcessor(libAndroid.room.compiler)
    // Room注解处理器(Kotlin-KAPT)
    // kapt(libAndroid.room.compiler)
    // Room注解处理器(Kotlin-KSP)
    ksp(libAndroid.room.compiler)
}
