@file:Suppress("UnstableApiUsage")

val versionMinSDK: Int = agp.versions.minSdk.get().toInt()
val versionCompileSDK: Int = agp.versions.compileSdk.get().toInt()
val versionTargetSDK: Int = agp.versions.targetSdk.get().toInt()
val versionModuleCode: Int = agp.versions.moduleCode.get().toInt()
val versionModuleName: String = agp.versions.moduleName.get()

plugins {
    alias(libAndroid.plugins.application)
    alias(libAndroid.plugins.kotlin)
    id("jacoco")
    id("org.jetbrains.kotlinx.kover")
}

android {
    namespace = "net.bi4vmr.study"
    compileSdk = versionCompileSDK

    defaultConfig {
        applicationId = "net.bi4vmr.study.tool.test.base"
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
            // isTestCoverageEnabled = true
            enableUnitTestCoverage = true
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
    }
}

dependencies {
    implementation(libAndroid.bundles.appBaseKT)

    testImplementation(libJava.junit4)
}

// Jacoco配置
jacoco {
    // 指定Jacoco版本
    toolVersion = "0.8.13"
}

// 在Gradle Test任务执行后自动执行Jacoco任务
tasks.withType<Test> {
    // jacoco.includeNoLocationClasses = true
    // jacoco.excludes = listOf("jdk.internal.*")
    ignoreFailures = true

    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }

    finalizedBy(tasks.named("jacocoTestReport"))
}

val fileFilter = listOf(
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "android/**/*.*",
    "**/databinding",
    "**/*Activity.class",
)

val fileFilter2 = files(
    "${layout.buildDirectory.get()}/intermediates/javac/debug/classes/net/bi4vmr/study/MainActivity.class"
)

tasks.register<JacocoReport>("jacocoTestReport") {
    group = "Reporting"
    description = "Generate Jacoco coverage reports after running tests."

    reports {
        xml.required = false
        csv.required = false
        html.required = true
        html.outputLocation.set(file("${layout.buildDirectory.get()}/reports/jacoco/jacocoTestReport/html"))
    }

    val debugTree = fileTree("${layout.buildDirectory.get()}/intermediates/javac/debug/classes") {
        exclude(fileFilter)
    } + fileFilter2

    val kotlinDebugTree = fileTree("${layout.buildDirectory.get()}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }
    debugTree.forEach {
        println("Debug Tree: ${it.absolutePath}")
    }
    kotlinDebugTree.forEach {
        println("KT Debug Tree: ${it.absolutePath}")
    }

    val sourceCodeMainJava = "$projectDir/src/main/java"
    val sourceCodeMainKotlin = "$projectDir/src/main/kotlin"

    // 注册源码目录
    sourceDirectories.setFrom(files(sourceCodeMainJava, sourceCodeMainKotlin))
    // 注册字节码目录
    classDirectories.setFrom(files(debugTree, kotlinDebugTree))
    // 添加EC文件
    executionData.setFrom(
        fileTree(layout.buildDirectory.get()) {
            include(
                "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",
                "outputs/code_coverage/debugAndroidTest/connected/**/*.ec"
            )
        }
    )
}
