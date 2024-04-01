plugins {
    id("com.android.application")
}

android {
    namespace = "net.bi4vmr.study"
    compileSdk = 33

    defaultConfig {
        applicationId = "net.bi4vmr.study.overview.helloworld"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.8.0")
}
