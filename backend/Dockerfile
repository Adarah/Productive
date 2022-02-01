# syntax=docker/dockerfile:1
ARG base=openjdk:17
ARG workdir=/app

FROM ${base} AS builder
WORKDIR ${workdir}
COPY

FROM ${base} AS runtime
WORKDIR ${workdir}
COPY --from=builder
ENTRYPOINT ["java", "-jar", ""]
