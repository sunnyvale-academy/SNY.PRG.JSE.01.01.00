# Java2 Demo
## Mandatory prerequisite steps
```
sudo yum update && sudo yum install maven # (RedHat like Linux)
sudo apt-get update && sudo apt-get install maven # (Ubuntu like Linux)
export JAVA_HOME=/path/to/jdk7
export PATH=JAVA_HOME/bin:$PATH
````

## To compile the application
```
cd <JAVA2DEMO_HOME>
mvn clean
mvn build
```
## To run the application
```
cd <JAVA2DEMO_HOME>
mvn exec:exec
```

## To modify java flags
```
vi pom.xml
```