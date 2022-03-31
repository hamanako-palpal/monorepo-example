rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:generated:orm"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
