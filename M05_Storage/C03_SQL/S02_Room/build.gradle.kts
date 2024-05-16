plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kotlin.ksp)
}

val versionMinSDK: Int = app.versions.minSdk.get().toInt()
val versionCompileSDK: Int = app.versions.compileSdk.get().toInt()
val versionTargetSDK: Int = app.versions.targetSdk.get().toInt()
val versionModuleCode: Int = app.versions.moduleCode.get().toInt()
val versionModuleName: String = app.versions.moduleName.get()

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
    // Room核心
    implementation(libs.android.room.runtime)
    // Room Kotlin语言扩展
    implementation(libs.android.room.ktx)

    // Room注解处理器(Java)
    // annotationProcessor(libs.android.room.compiler)
    // Room注解处理器(Kotlin-KAPT)
    // kapt(libs.android.room.compiler)
    // Room注解处理器(Kotlin-KSP)
    ksp(libs.android.room.compiler)
}
