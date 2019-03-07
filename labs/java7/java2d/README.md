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

## To modify Java GC flags
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

## To modify Java G1 GC flags
```
vi pom.xml
```
Change/comment/uncomment the following
```
<!-- G1 -->
<argument>-XX:+UseG1GC</argument>
<argument>-XX:MaxGCPauseMillis=200</argument>
<argument>-XX:InitiatingHeapOccupancyPercent=45</argument>
<argument>-XX:G1ReservePercent=10</argument>
<argument>-XX:ConcGCThreads=2</argument>
```

## To enable/disable Java Flight Recorder 
```
vi pom.xml
```
Change/comment/uncomment the following
```
<!-- Flight Recorder -->
<argument>-XX:+UnlockCommercialFeatures</argument>
<argument>-XX:+FlightRecorder</argument>
<!--<argument>-XX:FlightRecorderOptions=defaultrecording=true,settings=super_verbose_jfr_template.jfc</argument>-->
```

## To analyse the data manually

Inspect gc.log
```
vi gc.log
```

## To analyse the data using gceasy.io (browser based)
Upload gc.log file to http://gceasy.io

## To analyse the data using gceasy.io (REST API based)
Obtain an API KEY by registering [here](https://gceasy.io/gc-registration.jsp)

Upload gc.log file using the following command
```
curl -X POST --data-binary @./gc.log https://api.gceasy.io/analyzeGC?apiKey={API_KEY_SENT_IN_EMAIL} --header "Content-Type:text"
```
