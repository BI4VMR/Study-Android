// Gradle插件声明
plugins {
    alias(libAndroid.plugins.application) apply false
    alias(libAndroid.plugins.library) apply false
    alias(libAndroid.plugins.kotlin) apply false
    id("net.bi4vmr.gradle.repository") apply false
}

allprojects {
    project.apply( plugin ="net.bi4vmr.gradle.repository")
}
