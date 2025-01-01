# 简介

Android应用程序开发学习示例代码。

# AOSP系统签名

本工程内置了AOSP系统签名文件，如果部分API需要验证签名权限，可以应用该签名后进行调试。

"build.gradle.kts":

```kotlin
android {
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
}
```

<!-- Hide

# 提交命令
常用：

```text
msg=$(uuidgen | awk '{print toupper($0)}'); git add .; git commit -m "$msg"; git push;
```

完整：

```text
msg=$(uuidgen | awk '{print toupper($0)}'); git add .; git commit -m "$msg"; git push github; git push private;
```

-->
