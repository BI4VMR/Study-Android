# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
#
# 指定该工程可使用的最低CMake版本
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
#
# 指定工程名称
project("jnitest")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#
# In this top level CMakeLists.txt, ${CMAKE_PROJECT_NAME} is used to define
# the target library name; in the sub-module's CMakeLists.txt, ${PROJECT_NAME}
# is preferred for the same purpose.
#
# In order to load a library into your app from Java/Kotlin, you must call
# System.loadLibrary() and pass the name of the library defined here;
# for GameActivity/NativeActivity derived applications, the same library name must be
# used in the AndroidManifest.xml file.
#
# 编译指令，生成名称为"libjnitest.so"的构建产物。
add_library(
        # 库文件名称，此处引用的变量为"project()"指令的值"jnitest"。
        ${CMAKE_PROJECT_NAME}
        # 库文件类型。
        # "SHARED"为动态库；"STATIC"为静态库。
        SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        # 列出该库包含的所有源代码文件
        jni.cpp)

# Specifies libraries CMake should link to your target library. You
# can link libraries from various origins, such as libraries defined in this
# build script, prebuilt third-party libraries, or Android system libraries.
#
# 指定构建产物的外部依赖
target_link_libraries(
        # 声明该配置需要对何构建产物生效，此处为"jnitest"。
        ${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        # 列出依赖组件名称
        android
        log)
