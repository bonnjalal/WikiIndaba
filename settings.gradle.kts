pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
//        maven { url = java.net.URI("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = java.net.URI("https://jitpack.io") }
        google()
        mavenCentral()

    }
}

rootProject.name = "Wiki Indaba"
include(":app")
