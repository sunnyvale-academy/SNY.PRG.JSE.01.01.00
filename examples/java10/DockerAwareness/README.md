
# Experimenting Java 10 Docker full awareness



## Prerequisites:

Having Git, Virtualbox and Vagrant installed, please refer to:

- https://git-scm.com/download
- https://www.vagrantup.com/downloads.html
- https://www.virtualbox.org/wiki/Downloads

## Virtual machine provisioning:

```
user@yourpc$ vagrant plugin install vagrant-hostmanager
user@yourpc$ git clone https://github.com/sunnyvale-academy/SNY.PRG.JSE.01.01.00.git
user@yourpc$ cd SNY.PRG.JSE.01.01.00/examples/java10/DockerAwareness
user@yourpc$ vagrant up
user@yourpc$ vagrant ssh 
```

## Let's test:

### Java 8

```
vagrant@docker:~$ docker run \
                        --rm \
                        docker-test:jdk8

System properties
Cores       : 2
Memory (Max): 239
```
Initially, when the Java 8 container starts, it sees 2 cores and allocates 241MB of memory (1024/4=256). Now let’s try to limit the resources and see the results.

```
vagrant@docker:~$ docker run \
                        --rm \
                        -c 512 \
                        -m 512MB \
                        docker-test:jdk8

System properties
Cores       : 2
Memory (Max): 239
```
Here the -c 512 sets the CPU Shares to 512, which advises using half of the available CPU time. And the -m 512MB limits the memory to given number. As expected, these arguments are not working in this Java version.

However, Java 8 update 151 has the CPU Sets improvement. This time let’s try with setting the --cpuset-cpus to a single core.

```
vagrant@docker:~$ docker run \
                        --rm \
                        --cpuset-cpus 0 \
                        -m 512MB \
                        docker-test:jdk8

System properties
Cores       : 1
Memory (Max): 239
```
And it’s working. This version also allows us to use the -XX:+UseCGroupMemoryLimitForHeap option to get the correct memory limit.

```
vagrant@docker:~$ docker run \
                        --rm \
                        --cpuset-cpus 0 \
                        -m 512MB \
                        -e JAVA_OPT="-XX:+UnlockExperimentalVMOptions \
                        -XX:+UseCGroupMemoryLimitForHeap" \
                        docker-test:jdk8

System properties
Cores       : 1
Memory (Max): 123
```
With the help of this option, finally, 123MB of heap space is allocated, which perfectly makes sense for the upper limit of 512MB.

### Java 9

It would be enough to repeat the last step from the previous section since the functionality is same.

```
vagrant@docker:~$ docker run \
                        --rm \
                        --cpuset-cpus 0 \
                        -m 512MB \
                        -e JAVA_OPT="-XX:+UnlockExperimentalVMOptions \
                        -XX:+UseCGroupMemoryLimitForHeap" \
                        docker-test:jdk9

System properties
Cores       : 1
Memory (Max): 123
```

As expected, Java 9 recognized the CPU Sets and the memory limits when -XX:+UseCGroupMemoryLimitForHeap is used.

### Java 10

Since Java 10 is the Docker-aware version, resource limits should have taken effect without any explicit configuration.

```
vagrant@docker:~$ docker run \
                        --rm \
                        --cpuset-cpus 0 \
                        -m 512MB \
                        docker-test:jdk10

System properties
Cores       : 1
Memory (Max): 123
```

The previous snippet shows that CPU Sets are handled correctly. Now let’s try with setting CPU Shares:

```
vagrant@docker:~$ docker run \
                        --rm \
                        -c 512 \
                        -m 512MB \
                        docker-test:jdk10

System properties
Cores       : 1
Memory (Max): 123
```

It’s working as expected. Also, it’s worth to see this feature can be disabled via the -XX:-UseContainerSupport option (note that it starts with - after the -XX: prefix):

```
vagrant@docker:~$ docker run \
                        --rm \
                        -c 512 \
                        -m 512MB \
                        -e JAVA_OPT=-XX:-UseContainerSupport \
                        docker-test:jdk10

System properties
Cores       : 2
Memory (Max): 239
```

This time JVM reads the configuration from the Docker machine. So these outputs show how the resource limits are correctly handled in Java 10. As mentioned in the Improvements section, this version also includes changes in Attach API. To demonstrate this, first, let’s start the DockerTest container using JDK 10 in loop mode

```
vagrant@docker:~$ docker run \
                        --rm \
                        -ti \
                        -e JAVA_OPT=-Dloop=true \
                        docker-test:jdk10

System properties
Cores       : 2
Memory (Max): 239

```

```
vagrant@docker:~$ ps -ef | grep DockerTest

root     23778  0.6  2.6 2505204 26620 ?       Sl+  03:35   0:00 /jdk-10/bin/java -Dloop=true DockerTest

vagrant@docker:~$ sudo jstack 23778

2019-05-04 03:36:42
Full thread dump OpenJDK 64-Bit Server VM (10+46 mixed mode):

Threads class SMR info:
_java_thread_list=0x00007f0ab80028e0, length=10, elements={
0x00007f0ae8010800, 0x00007f0ae8089800, 0x00007f0ae808b800, 0x00007f0ae80a4800,
0x00007f0ae80a6800, 0x00007f0ae80a8800, 0x00007f0ae80aa800, 0x00007f0ae8124800,
0x00007f0ae8132800, 0x00007f0ab8001000
}

"main" #1 prio=5 os_prio=0 tid=0x00007f0ae8010800 nid=0x7 waiting on condition  [0x00007f0af0fe1000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(java.base@10/Native Method)
        at DockerTest.main(DockerTest.java:10)

```

It’s important to mention that 23778 is the PID visible on the host machine. For example, below output shows the actual PID inside of the container, which is different as expected (6).


```
vagrant@docker:~$ docker exec \
                        -ti 0e336e755f70 \
                        /jdk-10/bin/jps

6 DockerTest
```

## Conclusion
Even though there’re a couple of features added prior to Java 10, the newest Java release is the most container ready version experienced so far. This example  solely focused on single Docker containers. It would be good to experiment how Java 10 plays under orchestration frameworks as well (ie: Kubernetes).