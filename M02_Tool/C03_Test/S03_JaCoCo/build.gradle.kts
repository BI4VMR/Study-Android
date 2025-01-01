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
    // 声明JaCoCo插件
    id("jacoco")
}

android {
    namespace = "net.bi4vmr.study"
    compileSdk = versionCompileSDK

    defaultConfig {
        applicationId = "net.bi4vmr.study.tool.test.jacoco"
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
            // 开启单元测试覆盖率检测
            // isTestCoverageEnabled = true
            // 开启单元测试覆盖率检测（新版API）
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
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
    implementation(libKotlin.ktx.coroutines.core)
    implementation(libKotlin.ktx.coroutines.test)

    testImplementation(libJava.junit4)
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
    // Kotlin协程
    // "**/*$1*.class",
    // "**/*$2*.class",
    // "**/*$3*.class",
    // "**/*$4*.class",
    // "**/*$5*.class",
    // "**/*$6*.class",
    // "**/*$7*.class",
    // "**/*$8*.class",
    // "**/*$9*.class",
    "**/*$1.class",
    "**/*$2.class",
    "**/*$3.class",
    "**/*$4.class",
    "**/*$5.class",
    "**/*$6.class",
    "**/*$7.class",
    "**/*$8.class",
    "**/*$9.class",

    // Android
    "**/R.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "android/**",
    "androidx/**",
    "**/databinding",
    "**/BR.class",

    // UI
    "**/*Activity.*",
    "**/*Fragment.*",
    "**/*View.*"
)
// 匹配在排除目录中但需要包含的文件
val includeFilter: List<String> = listOf(
    "**/MainActivity1.class",
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
