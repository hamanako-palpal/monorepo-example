rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:generated:orm",
    "libs:generated:orm-plugin",
    "libs:domains:contracts",
    "libs:domains:customers",
    "libs:util",
    "webapps:gateway",
    "webapps:service-contracts",
    "webapps:service-customers",
    "webapps:shared"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
