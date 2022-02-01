load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

java_library(
    name = "backend-lib",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**"]),
    runtime_deps = [
        "@maven//:org_postgresql_postgresql",
        "@maven//:org_springframework_boot_spring_boot_devtools",
        "@maven//:org_springframework_boot_spring_boot_loader",
    ],
    deps = [
        "@maven//:org_flywaydb_flyway_core",
        "@maven//:org_projectlombok_lombok",
        "@maven//:org_springframework_boot_spring_boot",
        "@maven//:org_springframework_boot_spring_boot_autoconfigure",
        "@maven//:org_springframework_boot_spring_boot_configuration_processor",
        "@maven//:org_springframework_boot_spring_boot_starter_actuator",
        "@maven//:org_springframework_boot_spring_boot_starter_data_jpa",
        "@maven//:org_springframework_boot_spring_boot_starter_security",
        "@maven//:org_springframework_boot_spring_boot_starter_validation",
        "@maven//:org_springframework_boot_spring_boot_starter_web",
        "@maven//:org_springframework_spring_web",
    ],
)

java_test(
    name = "backend-test",
    srcs = glob(["src/test/java/com/lucasharada/productivebe/*.java"]),
    test_class = "com.lucasharada.productivebe.ProductiveBeApplicationTest",
    deps = [
        ":backend-lib",
        "@maven//:org_junit_jupiter_junit_jupiter_api",
        "@maven//:org_springframework_boot_spring_boot_starter_test",
        "@maven//:org_springframework_boot_spring_boot_test",
        "@maven//:org_springframework_security_spring_security_test",
        "@maven//:org_testcontainers_junit_jupiter",
        "@maven//:org_testcontainers_postgresql",
    ],
)

load("@rules_spring//springboot:springboot.bzl", "springboot")

springboot(
    name = "backend",
    boot_app_class = "com.lucasharada.productivebe.ProductiveBeApplication",
    java_library = ":backend-lib",
)
