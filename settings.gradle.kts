plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "patrones1"

include("factory")
include("factory-test")
include("factory-crud")
include("composite")

