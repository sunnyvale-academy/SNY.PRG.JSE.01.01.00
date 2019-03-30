
# HelloModularWorld application

Every path is relative from here (where this README.md is located)

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