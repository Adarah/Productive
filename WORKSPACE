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
        "org.apache.logging.log4j:log4j-slf4j18-impl:2.17.1",
        "org.springframework.boot:spring-boot-starter-actuator:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-data-jpa:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-security:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-starter-validation:{}".format(SPRING_BOOT_VERSION),
        "org.springframework.boot:spring-boot-loader:{}".format(SPRING_BOOT_VERSION),
        maven.artifact(
            "org.springframework.boot",
            "spring-boot-starter-log4j2",
            SPRING_BOOT_VERSION,
            exclusions = [
                "org.apache.logging.log4j:log4j-slf4j-impl",
            ],
        ),
        "org.springframework.boot:spring-boot-starter-web:{}".format(SPRING_BOOT_VERSION),
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
    excluded_artifacts = [
        "org.springframework.boot:spring-boot-starter-logging",
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

#######################################
### Go toolchain
#######################################
# This toolchain is needed to allow crosscompiling docker image from darwin-arm64 to linux-amd64.
# Not sure why but io_bazel_rules_docker fails if this is called after it.
http_archive(
    name = "io_bazel_rules_go",
    sha256 = "d6b2513456fe2229811da7eb67a444be7785f5323c6708b38d851d2b51e54d83",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_go/releases/download/v0.30.0/rules_go-v0.30.0.zip",
        "https://github.com/bazelbuild/rules_go/releases/download/v0.30.0/rules_go-v0.30.0.zip",
    ],
)

http_archive(
    name = "bazel_gazelle",
    sha256 = "de69a09dc70417580aabf20a28619bb3ef60d038470c7cf8442fafcf627c21cb",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.24.0/bazel-gazelle-v0.24.0.tar.gz",
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.24.0/bazel-gazelle-v0.24.0.tar.gz",
    ],
)

load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")
load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

go_rules_dependencies()

go_register_toolchains(version = "1.17.6")

gazelle_dependencies()

#######################################
### Docker support
#######################################
http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "85ffff62a4c22a74dbd98d05da6cf40f497344b3dbf1e1ab0a37ab2a1a6ca014",
    strip_prefix = "rules_docker-0.23.0",
    urls = ["https://github.com/bazelbuild/rules_docker/releases/download/v0.23.0/rules_docker-v0.23.0.tar.gz"],
)

#load(
#    "@io_bazel_rules_docker//toolchains/docker:toolchain.bzl",
#    docker_toolchain_configure = "toolchain_configure",
#)
#
#docker_toolchain_configure(
#    name = "docker_config",
#    # Replace this with an absolute path to a directory which has a custom docker
#    # client config.json. Note relative paths are not supported.
#    # Docker allows you to specify custom authentication credentials
#    # in the client configuration JSON file.
#    # See https://docs.docker.com/engine/reference/commandline/cli/#configuration-files
#    # for more details.
#    client_config = "${DOCKER_CONFIG}",
#)

load(
    "@io_bazel_rules_docker//repositories:repositories.bzl",
    container_repositories = "repositories",
)

container_repositories()

load("@io_bazel_rules_docker//repositories:deps.bzl", container_deps = "deps")

container_deps()

load(
    "@io_bazel_rules_docker//container:container.bzl",
    "container_pull",
)

container_pull(
    name = "java_base",
    # 'tag' is also supported, but digest is encouraged for reproducibility.
    digest = "sha256:28ce3bf1f7612d6624b3899937cee7880b88b7740632d002724ddf77a22e37e8",
    registry = "index.docker.io",
    repository = "openjdk",
)
