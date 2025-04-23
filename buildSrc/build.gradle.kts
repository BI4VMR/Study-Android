// Gradle插件声明
plugins {
    // id("org.gradle.kotlin.kotlin-dsl")
    `kotlin-dsl`
    // `java-gradle-plugin`
}

repositories {
    mavenCentral()
    // maven {
    //     isAllowInsecureProtocol = true
    //     setUrl("http://127.0.0.1:8081/repository/maven-mirror-tencent/")
    // }
}

gradlePlugin {
    plugins {
        create("greeting") {
            id = "net.bi4vmr.gradle.repository" // 这里是插件的ID
            implementationClass = "net.bi4vmr.gradle.RepositoryPlugin" // 这里是包名+类名
        }
    }
}
