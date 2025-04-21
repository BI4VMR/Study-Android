@file:Suppress("UnstableApiUsage")

// 构建工具的依赖配置
pluginManagement {
    // 声明Gradle插件仓库
    repositories {
        // 添加本地私有仓库与代理镜像，无法直连时应当禁用该配置。
        val hostName: String = java.net.InetAddress.getLocalHost().hostName
        println("Current host name is [$hostName]")
        var isInPrivateLAN = false
        run {
            java.net.NetworkInterface.getNetworkInterfaces().toList().forEach {
                it.inetAddresses.toList().forEach { addr ->
                    if ((addr is java.net.Inet4Address) && (addr.hostAddress.startsWith("172.16."))) {
                        isInPrivateLAN = true
                        return@run
                    }
                }
            }
        }
        println("Current host in private LAN? [$isInPrivateLAN]")

        if (hostName.startsWith("BI4VMR") && isInPrivateLAN) {
            println("Current host is in private network, add LAN repositorys.")
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-mirror-tencent/")
            }
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-private/")
            }
        } else {
            if (java.net.InetAddress.getByName("192.168.128.1").isReachable(2000)) {
                println("Current host is not in private network, add VPN repositorys.")
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-mirror-tencent/")
                }
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-private/")
                }
            } else {
                println("Current host is not in private network, add LOCAL repositorys.")
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://127.0.0.1:8081/repository/maven-mirror-tencent/")
                }
                mavenLocal()
            }
        }

        // 腾讯云仓库镜像：Maven中心仓库+Google+JCenter
        maven { setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        // 阿里云仓库镜像：Maven中心仓库+JCenter
        maven { setUrl("https://maven.aliyun.com/repository/public/") }
        // 阿里云仓库镜像：Gradle社区插件
        maven { setUrl("https://maven.aliyun.com/repository/gradle-plugin/") }

        mavenCentral()
        gradlePluginPortal()
    }
}

// 所有模块的依赖配置
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    // 声明Maven组件仓库
    repositories {
        // 添加本地私有仓库与代理镜像，无法直连时应当禁用该配置。
        val hostName: String = java.net.InetAddress.getLocalHost().hostName
        var isInPrivateLAN = false
        run {
            java.net.NetworkInterface.getNetworkInterfaces().toList().forEach {
                it.inetAddresses.toList().forEach { addr ->
                    if ((addr is java.net.Inet4Address) && (addr.hostAddress.startsWith("172.16."))) {
                        isInPrivateLAN = true
                        return@run
                    }
                }
            }
        }

        if (hostName.startsWith("BI4VMR") && isInPrivateLAN) {
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-mirror-tencent/")
            }
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-jitpack/")
            }
            maven {
                isAllowInsecureProtocol = true
                setUrl("http://172.16.5.1:8081/repository/maven-private/")
            }
        } else {
            if (java.net.InetAddress.getByName("192.168.128.1").isReachable(2000)) {
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-mirror-tencent/")
                }
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-jitpack/")
                }
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://192.168.128.1:8081/repository/maven-private/")
                }
            } else {
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://127.0.0.1:8081/repository/maven-mirror-tencent/")
                }
                maven {
                    isAllowInsecureProtocol = true
                    setUrl("http://127.0.0.1:8081/repository/maven-mirror-jitpack/")
                }
                mavenLocal()
            }
        }

        // 腾讯云仓库镜像：Maven中心仓库+Google+JCenter
        maven { setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
        // 阿里云仓库镜像：Maven中心仓库+JCenter
        maven { setUrl("https://maven.aliyun.com/repository/public/") }
        // 阿里云仓库镜像：Google
        maven { setUrl("https://maven.aliyun.com/repository/google/") }

        mavenCentral()
        google()
        maven { setUrl("https://jitpack.io/") }
    }

    // 版本管理配置
    versionCatalogs {
        // 公共组件(Java)
        create("libJava") {
            from(files("misc/version/dependency_public_java.toml"))
        }

        // 公共组件(Kotlin)
        create("libKotlin") {
            from(files("misc/version/dependency_public_kotlin.toml"))
        }

        // 公共组件(Android)
        create("libAndroid") {
            from(files("misc/version/dependency_public_android.toml"))
        }

        // 私有组件(Java)
        create("privateLibJava") {
            from(files("misc/version/dependency_private_java.toml"))
        }

        // 私有组件(Android)
        create("privateLibAndroid") {
            from(files("misc/version/dependency_private_android.toml"))
        }

        // AGP相关配置
        create("agp") {
            from(files("misc/version/agp.toml"))
        }
    }
}

/* ----- 工程结构声明 ----- */
// 主工程名称
rootProject.name = "Study-Android"

/* ----- 基础知识 ----- */
include(":M01_Overview:C00_Temporary")
include(":M01_Overview:C01_HelloWorld")

/* ----- 实用工具 ----- */
include(":M02_Tool:C01_Common:S01_ADB")
include(":M02_Tool:C01_Common:S02_AVD")
include(":M02_Tool:C01_Common:S03_Logcat")
include(":M02_Tool:C01_Common:S04_Scrcpy")
include(":M02_Tool:C02_Build:S01_Base")
include(":M02_Tool:C02_Build:S02_Advance")

/* ----- 用户界面 ----- */
include(":M03_UI:C01_Common:S01_View")
include(":M03_UI:C01_Common:S02_Activity")
include(":M03_UI:C01_Common:S03_Fragment")
include(":M03_UI:C01_Common:S04_Window")
include(":M03_UI:C01_Common:S05_Widget")
include(":M03_UI:C01_Common:S06_Wallpaper")
include(":M03_UI:C02_Resource:S01_Base")
include(":M03_UI:C02_Resource:S02_Text")
include(":M03_UI:C02_Resource:S03_Color")
include(":M03_UI:C02_Resource:S04_Image")
include(":M03_UI:C02_Resource:S05_Style")
include(":M03_UI:C03_LayoutManager:S01_Linear")
include(":M03_UI:C03_LayoutManager:S02_Table")
include(":M03_UI:C03_LayoutManager:S03_Frame")
include(":M03_UI:C03_LayoutManager:S04_Relative")
include(":M03_UI:C03_LayoutManager:S05_Constraint")
include(":M03_UI:C04_CtrlBase:S01_TextView")
include(":M03_UI:C04_CtrlBase:S02_EditText")
include(":M03_UI:C04_CtrlBase:S03_Button")
include(":M03_UI:C04_CtrlBase:S04_RadioButton")
include(":M03_UI:C04_CtrlBase:S05_ToggleButton")
include(":M03_UI:C04_CtrlBase:S06_CheckBox")
include(":M03_UI:C04_CtrlBase:S07_ImageView")
include(":M03_UI:C04_CtrlBase:S10_SeekBar")
include(":M03_UI:C04_CtrlBase:S11_Toast")
include(":M03_UI:C04_CtrlBase:S12_AlertDialog")
include(":M03_UI:C04_CtrlBase:S13_TextClock")
include(":M03_UI:C04_CtrlBase:S14_AnalogClock")
include(":M03_UI:C05_CtrlExt:S01_RecyclerView")
include(":M03_UI:C05_CtrlExt:S02_ViewPager")
include(":M03_UI:C05_CtrlExt:S03_ViewPager2")
include(":M03_UI:C05_CtrlExt:S04_DialogFragment")
include(":M03_UI:C05_CtrlExt:S06_DrawerLayout")
include(":M03_UI:C06_CtrlMD:S01_MaterialButton")
include(":M03_UI:C06_CtrlMD:S02_FloatingActionButton")
include(":M03_UI:C06_CtrlMD:S04_Chip")
include(":M03_UI:C06_CtrlMD:S05_TabLayout")
include(":M03_UI:C08_CtrlCustom:S01_Base")
include(":M03_UI:C08_CtrlCustom:S02_AnalogClock")
include(":M03_UI:C10_Compose:S01_Base")
include(":M03_UI:C10_Compose:S02_Layout")

/* ----- 系统组件 ----- */
include(":M04_System:C01_Common:S01_Bundle")
include(":M04_System:C01_Common:S02_Intent")
include(":M04_System:C01_Common:S03_Application")
include(":M04_System:C01_Common:S04_Context")
include(":M04_System:C01_Common:S05_Parcelable")
include(":M04_System:C02_Service:S01_StartService")
include(":M04_System:C02_Service:S02_BindService")
include(":M04_System:C02_Service:S03_LifeCycle")
include(":M04_System:C02_Service:S04_Foreground")
include(":M04_System:C02_Service:S05_AIDL-Server")
include(":M04_System:C02_Service:S05_AIDL-Client")
include(":M04_System:C03_ContentProvider:S01_Base")
include(":M04_System:C03_ContentProvider:S02_Internal")
include(":M04_System:C03_ContentProvider:S03_RelationalData")
include(":M04_System:C03_ContentProvider:S04_BinaryData")
include(":M04_System:C03_ContentProvider:S05_DataObserver")
include(":M04_System:C03_ContentProvider:S06_RPC")
include(":M04_System:C04_Broadcast:S01_System")
include(":M04_System:C04_Broadcast:S02_Normal")
include(":M04_System:C04_Broadcast:S03_Ordered")
include(":M04_System:C04_Broadcast:S04_Local")
include(":M04_System:C05_Ability:S01_Universal")
include(":M04_System:C05_Ability:S02_Permission")
include(":M04_System:C05_Ability:S03_Notification")
include(":M04_System:C05_Ability:S04_Configuration")
include(":M04_System:C05_Ability:S05_Package")
include(":M04_System:C05_Ability:S06_UsageStats")
include(":M04_System:C05_Ability:S07_Process")
include(":M04_System:C05_Ability:S08_User")
include(":M04_System:C05_Ability:S09_Key")
include(":M04_System:C05_Ability:S10_NDK")
include(":M04_System:C06_Concurrent:S01_Base")
include(":M04_System:C06_Concurrent:S02_Handler")

/* ----- 数据存储 ----- */
include(":M05_Storage:C01_File:S01_Base")
include(":M05_Storage:C02_KV:S01_SharedPreferences")
include(":M05_Storage:C03_SQL:S01_SQLite")
include(":M05_Storage:C03_SQL:S02_Room")

/* ----- 网络通讯 ----- */
include(":M06_Network:C01_Common:S02_ConnectState")

/* ----- 媒体技术 ----- */
include(":M07_Media:C01_Common:S01_MediaSession")
include(":M07_Media:C01_Common:S02_MediaStore")
include(":M07_Media:C03_Audio:S01_AudioFocus")

/* ----- 工程架构 ----- */
include(":M08_Architecture:C02_Component:S01_ViewModel")
include(":M08_Architecture:C02_Component:S02_LiveData")
include(":M08_Architecture:C02_Component:S03_ViewBinding")
include(":M08_Architecture:C02_Component:S04_DataBinding")
include(":M08_Architecture:C02_Component:S05_Compose")
