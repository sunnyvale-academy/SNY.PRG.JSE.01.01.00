
# HelloModularWorld application

Every path is relative from here (where this README.md is located)

Prerequite steps

```
$ export JAVA_HOME=/path/to/jdk9
$ export PATH=$JAVA_HOME/bin:$PATH
```


To build the modules using javac executable 

```
$ javac -d modules --module-source-path world/src/main/java  world/src/main/java/com.world/com/world/World.java
$ javac -d modules --module-path modules --module-source-path greeting/src/main/java  greeting/src/main/java/com.greeting/com/greeting/Hello.java
```

To run the application using java executable

```
$ java --module-path modules --module com.greeting/com.greeting.Hello
```

To build the modules using Maven

```
$ mvn clean package
```

To run the application using Maven

```
$ cd greeting
$ mvn exec:exec
```

To create a Custom Runtime Image (modules directory has to be populated with application modules first)

```
$ jlink \
    --module-path modules:$JAVA_HOME/jmods \
    --add-modules com.greeting \
    --launcher Hello=com.greeting/com.greeting.Hello \
    --strip-debug \
    --compress=2 \
    --output myimage

```

To run the main module in the Custom Runtime Image (no module-path needed)

```
$ myimage/bin/java --module com.greeting/com.greeting.Hello
```

To run the Custom Runtime Image using its launcher (POSIX shell script text executable, ASCII text)
```
$ myimage/bin/Hello
```
To generate a Docker image containing the previously build Java Custom Runtime Image (ref. Dockerfile)
```
$ docker build -t hello-modular-world:1.0 .
```

To run the hello-modular-world:1.0 Docker image
```
$ docker run --rm hello-modular-world:1.0 
```
