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
Change/comment/uncomment the following
```
<!-- GC -->
<argument>-verbose:gc</argument>
<argument>-Xloggc:gc.log</argument>
<argument>-XX:+PrintGCTimeStamps</argument>
<argument>-XX:+PrintGCDateStamps</argument>
<argument>-XX:+PrintGCDetails</argument>
<argument>-XX:+PrintGCApplicationStoppedTime</argument>
<argument>-XX:+PrintGCApplicationConcurrentTime</argument>
<argument>-XX:+UseGCLogFileRotation</argument>
<argument>-XX:NumberOfGCLogFiles=5</argument>
<argument>-XX:GCLogFileSize=2M</argument>
<argument>-XX:+PrintFlagsFinal</argument>
```