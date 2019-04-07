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
ModularProject$ mvn build
ModularProject$ mvn install
```

To test the application
```
ModularProject$ mvn -Dtest=modularproject.test.ClientTester test
```

To run the application
```
ModularProject$ cd client
client$ mvn exec:exec
```


