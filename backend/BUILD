load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

java_library(
    name = "backend-lib",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**"]),
    runtime_deps = [
        "@maven//:org_postgresql_postgresql",
        "@maven//:org_springframework_boot_spring_boot_devtools",
        "@maven//:org_springframework_boot_spring_boot_loader",
        "@maven//:org_springframework_boot_spring_boot_starter_tomcat",
    ],
    deps = [
        "@maven//:org_apache_logging_log4j_log4j_slf4j18_impl",
        "@maven//:org_flywaydb_flyway_core",
        "@maven//:org_projectlombok_lombok",
        "@maven//:org_slf4j_slf4j_api",
        "@maven//:org_springframework_boot_spring_boot",
        "@maven//:org_springframework_boot_spring_boot_autoconfigure",
        "@maven//:org_springframework_boot_spring_boot_configuration_processor",
        "@maven//:org_springframework_boot_spring_boot_starter_actuator",
        "@maven//:org_springframework_boot_spring_boot_starter_data_jpa",
        "@maven//:org_springframework_boot_spring_boot_starter_log4j2",
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
    bazelrun_jvm_flags = "-Dspring.profiles.active=dev",
    boot_app_class = "com.lucasharada.productivebe.ProductiveBeApplication",
    java_library = ":backend-lib",
)

load("@io_bazel_rules_docker//container:container.bzl", "container_image", "container_push")

container_image(
    name = "docker",
    base = "@java_base//image",
    entrypoint = [
        "java",
        "-jar",
        "backend.jar",
    ],
    files = ["backend.jar"],
    ports = ["8080"],
)

container_push(
    name = "docker_push",
    format = "Docker",
    image = ":docker",
    registry = "index.docker.io",
    repository = "adarah/productive-be",
    skip_unchanged_digest = True,
    tag = "latest",
)
