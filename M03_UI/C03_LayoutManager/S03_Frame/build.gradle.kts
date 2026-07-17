plugins {
    alias(libAndroid.plugins.application)
    alias(libAndroid.plugins.kotlin)
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
        applicationId = "net.bi4vmr.study.ui.layout.frame"
        minSdk = versionMinSDK
        targetSdk = versionTargetSDK
        versionCode = versionModuleCode
        versionName = versionModuleName
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

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(libAndroid.bundles.appBaseKT)
}
