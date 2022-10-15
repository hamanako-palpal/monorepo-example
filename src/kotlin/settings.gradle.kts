rootProject.name = "monorepo-example"
include(
    "libs:generated:graphql",
    "libs:generated:proto",
    "libs:util",
    "webapps:gateway:applications",
    "webapps:gateway:domains",
    "webapps:service-contracts:applications",
    "webapps:service-contracts:domains",
    "webapps:service-customers:applications",
    "webapps:service-customers:domains",
    "webapps:shared",
    "webapps:shared-test"
)
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}
