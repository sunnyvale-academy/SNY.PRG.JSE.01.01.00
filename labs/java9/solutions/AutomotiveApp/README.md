# AutomotiveApp migration solution

![ModularProject UML](../automotive_app.jpg)

Prerequite steps

```
~$ export JAVA_HOME=/path/to/jdk9
~$ export PATH=$JAVA_HOME/bin:$PATH
~$ cd AutomotiveApp
```

Every path is relative from here (where this README.md is located)

To build and install the JARs
```
AutomotiveApp$ mvn clean
AutomotiveApp$ mvn package
AutomotiveApp$ mvn install
```

To run the application
```
AutomotiveApp$ cd car
car$ mvn exec:exec
```
