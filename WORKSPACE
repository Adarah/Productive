#######################################
### Maven config
#######################################
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.2"

RULES_JVM_EXTERNAL_SHA = "cd1a77b7b02e8e008439ca76fd34f5b07aecb8c752961f9640dea15e9e5ba1ca"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@rules_jvm_external//:specs.bzl", "maven")

SPRING_BOOT_VERSION = "2.6.3"

maven_install(
    artifacts = [
        "org.springframework.boot:spring-boot-starter-actuator:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-data-jpa:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-security:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-validation:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-web:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-loader:{}".format(SPRING_BOOT_VERSION),
        "org.flywaydb:flyway-core:8.4.3",
        "org.postgresql:postgresql:42.3.1",
        "org.glassfish.jaxb:jaxb-runtime:2.3.2",
        maven.artifact(
            "org.springframework.boot",
            "spring-boot-devtools",
            SPRING_BOOT_VERSION,
            neverlink = True,
        ),
        maven.artifact(
            "org.springframework.boot",
            "spring-boot-configuration-processor",
            SPRING_BOOT_VERSION,
            neverlink = True,
        ),
        maven.artifact(
            "org.projectlombok",
            "lombok",
            "1.18.22",
            neverlink = True,
        ),
        maven.artifact(
            "org.springframework.boot",
            "spring-boot-starter-test",
            SPRING_BOOT_VERSION,
            testonly = True,
        ),
        maven.artifact(
            "org.springframework.security",
            "spring-security-test",
            "5.6.1",
            testonly = True,
        ),
        maven.artifact(
            "org.testcontainers",
            "junit-jupiter",
            "1.16.3",
            testonly = True,
        ),
        maven.artifact(
            "org.testcontainers",
            "postgresql",
            "1.16.3",
            testonly = True,
        ),
    ],
    fail_if_repin_required = True,
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()

#######################################
### Spring Boot jar export
#######################################
http_archive(
    name = "rules_spring",
    sha256 = "9385652bb92d365675d1ca7c963672a8091dc5940a9e307104d3c92e7a789c8e",
    urls = [
        "https://github.com/salesforce/rules_spring/releases/download/2.1.4/rules-spring-2.1.4.zip",
    ],
)
