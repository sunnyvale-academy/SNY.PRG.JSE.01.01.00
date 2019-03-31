# First stage: Runs JLink to create the custom JRE
FROM adoptopenjdk/openjdk9:x86_64-alpine-jdk-9.0.4.11 AS builder

MAINTAINER Denis Maggiorotto <denis.maggiorotto@sunnyvale.it>

ENV JAVA_HOME /opt/java/openjdk

WORKDIR /app

ADD world /usr/src/world

ADD greeting /usr/src/greeting

RUN javac -d /app/modules --module-source-path /usr/src/world/src/main/java  /usr/src/world/src/main/java/com.world/com/world/World.java && \
    javac -d /app/modules --module-path /app/modules --module-source-path /usr/src/greeting/src/main/java  /usr/src/greeting/src/main/java/com.greeting/com/greeting/Hello.java

RUN jlink \
    --module-path /app/modules:${JAVA_HOME}/jmods \
    --add-modules com.greeting \
    # optional (java.base is transitively addedd)
    --add-modules java.base \
    --launcher Hello=com.greeting/com.greeting.Hello \
    --strip-debug \
    --compress=2 \
    --output /app/myimage

# Second stage: Copies the custom JRE into our image and runs it
FROM  panga/alpine:3.7-glibc2.25

MAINTAINER Denis Maggiorotto <denis.maggiorotto@sunnyvale.it>

USER root 

RUN mkdir /app

WORKDIR /app

COPY --from=builder /app/myimage /app

ENTRYPOINT ["/app/bin/Hello"]
