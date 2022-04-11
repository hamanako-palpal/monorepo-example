rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:generated:orm",
    "libs:generated:orm-plugin",
    "libs:util",
    "subdomains:gateway",
    "subdomains:service-shop",
    "subdomains:service-user",
    "subdomains:shared"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
