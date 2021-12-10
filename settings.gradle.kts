rootProject.name = "spring-native"

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        gradlePluginPortal()
    }
}

include("native")
include("jvm")
