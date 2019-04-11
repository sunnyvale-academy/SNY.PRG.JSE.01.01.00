# Modular Project lab solution



Prerequite steps

```
~$ export JAVA_HOME=/path/to/jdk9
~$ export PATH=$JAVA_HOME/bin:$PATH
~$ cd ModularProject
```

Every path is relative from here (where this README.md is located)

To build and install the JARs
```
ModularProject$ mvn clean
ModularProject$ mvn package
ModularProject$ mvn install
```

To run the application
```
ModularProject$ cd client
client$ mvn exec:exec
```


