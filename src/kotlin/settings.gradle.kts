rootProject.name = "monorepo-example"
include(
    "libs:generated:proto",
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
