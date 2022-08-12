rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:generated:orm",
    "libs:generated:orm-plugin",
    "libs:util",
    "webapps:gateway",
    "webapps:service-contracts:applications",
    "webapps:service-contracts:domains",
    "webapps:service-customers:applications",
    "webapps:service-customers:domains",
    "webapps:shared"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
