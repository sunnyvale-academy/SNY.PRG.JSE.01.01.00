# GraalVM Native Images

## Prerequisites

To build a native image of your program use the **native-image** utility located in the bin directory of the GraalVM distribution. For compilation native-image depends on the local toolchain, so please make sure: **glibc-devel**, **zlib-devel** (header files for the C library and zlib) and **gcc** are available on your system.

Another prerequisite to consider is the maximum heap size. Physical memory for running a JVM-based application may be insufficient to build a native image. For server-based image building we allow to use 80% of the reported physical RAM for all servers together, but never more than 14GB per server (for exact details please consult the native-image source code). If you run with --no-server option, you will get the whole 80% of what is reported as physical RAM as the baseline. This mode respects -Xmx arguments additionally.

```
$ export JAVA_HOME=/path/to/GraalVM/Home # ie: export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ee-19.0.0/Contents/Home
$ export PATH=$JAVA_HOME/bin:$PATH

java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b31)
Java HotSpot(TM) GraalVM EE 19.0.0 (build 25.212-b31-jvmci-19-b01, mixed mode)
```

```
$ mkdir -p HelloNativeWorld/target/classes   
$ javac HelloNativeWorld/src/main/java/it/sunnyvale/academy/jsenewfeatures/ni/HelloNativeWorld.java -d HelloNativeWorld/target/classes 
```


```
$ java \
    -cp HelloNativeWorld/target/classes  \
    it.sunnyvale.academy.jsenewfeatures.ni.HelloNativeWorld
```

```
$ gu install native-image
```

```
$ native-image \
    -cp HelloNativeWorld/target/classes  \
    -H:+ReportExceptionStackTraces \
    it.sunnyvale.academy.jsenewfeatures.ni.HelloNativeWorld

 Build on Server(pid: 95309, port: 54394)
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]    classlist:     300.34 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]        (cap):   1,341.93 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]        setup:   2,706.20 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]   (typeflow):   2,949.75 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]    (objects):     946.86 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]   (features):     186.21 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]     analysis:   4,156.47 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]     (clinit):     105.97 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]     universe:     378.57 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]      (parse):     542.95 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]     (inline):   1,428.15 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]    (compile):   8,286.23 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]      compile:  10,620.35 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]        image:     524.48 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]        write:     301.49 ms
[it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld:95309]      [total]:  19,179.13 ms
```


```
$ ls -l 
3.8 M -rwxrwxr-x 1 xxxxx xxxxx 3.8M Dec 12 15:48 it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld
```


```
$ ./it.sunnyvale.academy.jsenewfeatures.ni.hellonativeworld

Hello Native World!
```

