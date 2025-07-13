@file:Suppress("UnstableApiUsage")

import org.gradle.api.tasks.testing.logging.TestExceptionFormat

val versionMinSDK: Int = agp.versions.minSdk.get().toInt()
val versionCompileSDK: Int = agp.versions.compileSdk.get().toInt()
val versionTargetSDK: Int = agp.versions.targetSdk.get().toInt()
val versionModuleCode: Int = agp.versions.moduleCode.get().toInt()
val versionModuleName: String = agp.versions.moduleName.get()

plugins {
    alias(libAndroid.plugins.application)
    alias(libAndroid.plugins.kotlin)
    id("jacoco")
    // id("org.jetbrains.kotlinx.kover")
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

        // 指定仪器化测试运行环境
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    /* 本地测试依赖项 */
    // JUnit4
    testImplementation(libJava.junit4)

    /* 仪器化测试依赖项 */
    // JUnit4
    androidTestImplementation(libJava.junit4)
    // AndroidX Test JUnit扩展
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    // AndroidX Test Espresso运行环境
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}

// Jacoco配置
jacoco {
    // 指定Jacoco版本
    toolVersion = "0.8.13"
}

// 测试任务配置
tasks.withType<Test> {
    // 允许测试失败后继续运行
    ignoreFailures = true

    // 配置测试日志
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
    }

    // 在Test任务执行后自动执行Jacoco报告生成任务
    finalizedBy(tasks.named("jacocoTestReport"))
}


val sourceCodeMainJava: String = "$projectDir/src/main/java/"
val sourceCodeMainKotlin: String = "$projectDir/src/main/kotlin/"

val buildDir: String = layout.buildDirectory.get().asFile.absolutePath
val javaClassDir: String = "$buildDir/intermediates/javac/debug/classes/"
val kotlinClassDir: String = "$buildDir/tmp/kotlin-classes/debug/"
// 匹配需要排除的文件
val excludeFilter: List<String> = listOf(
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "android/**/*.*",
    "**/databinding",
    "**/*Activity.class",
)
// 匹配在排除目录中但需要包含的文件
val includeFilter: List<String> = listOf(
    // "**/MainActivity.class",
)
// 组合生成需要统计的Java和Kotlin的字节码目录
val javaClassFiles: FileTree =
    fileTree(javaClassDir) { exclude(excludeFilter) } + fileTree(javaClassDir) { include(includeFilter) }
val kotlinClassFiles: FileTree =
    fileTree(kotlinClassDir) { exclude(excludeFilter) } + fileTree(kotlinClassDir) { include(includeFilter) }

// 定义Jacoco报告生成任务
tasks.register<JacocoReport>("jacocoTestReport") {
    group = "Reporting"
    description = "Generate Jacoco coverage reports after running tests."

    reports {
        xml.required = false
        csv.required = false
        html.required = true
        html.outputLocation.set(file("$buildDir/reports/jacoco/jacocoTestReport/html/"))
    }

    // 注册源码目录
    sourceDirectories.setFrom(files(sourceCodeMainJava, sourceCodeMainKotlin))
    // 注册字节码目录
    classDirectories.setFrom(javaClassFiles + kotlinClassFiles)
    // 注册EC文件
    executionData.setFrom(
        fileTree(buildDir) {
            include(
                "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec",
                "outputs/code_coverage/debugAndroidTest/connected/**/*.ec"
            )
        }
    )
}
