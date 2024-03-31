plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}

val versionMinSDK: Int = Integer.valueOf(libs.versions.android.minSdk.get())
val versionCompileSDK: Int = Integer.valueOf(libs.versions.android.compileSdk.get())
val versionTargetSDK: Int = Integer.valueOf(libs.versions.android.targetSdk.get())
val versionModuleCode: Int = Integer.valueOf(libs.versions.android.moduleCode.get())
val versionModuleName: String = libs.versions.android.moduleName.get()

@Suppress("UnstableApiUsage")
android {
    namespace = "net.bi4vmr.study"
    compileSdk = versionCompileSDK

    defaultConfig {
        applicationId = "net.bi4vmr.study.ui.ctrlbase.checkbox"
        minSdk = versionMinSDK
        targetSdk = versionTargetSDK
        versionCode = versionModuleCode
        versionName = versionModuleName
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

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation(libs.bundles.android.coreWithKT)
}
