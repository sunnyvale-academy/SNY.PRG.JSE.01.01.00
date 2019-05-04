# Improve Launch Times On Java 10 With Application Class-Data Sharing


## Using Class Data Sharing

1) create a list of classes to include in the archive (possibly with -XX:DumpLoadedClassList)
2) create an archive with the option -Xshare:dump
3) use the archive with the option -Xshare:on


The simplest way to get started with class-data sharing is to limit it to JDK classes, so we’ll do that first. We will then observe that a simple “Hello, World” JAR can be launched in almost half the time.

To build the modules using javac executable 

```
$ javac -d HelloModularWorld/modules --module-source-path HelloModularWorld/world/src/main/java  HelloModularWorld/world/src/main/java/com.world/com/world/World.java
$ javac -d HelloModularWorld/modules --module-path HelloModularWorld/modules --module-source-path HelloModularWorld/greeting/src/main/java  HelloModularWorld/greeting/src/main/java/com.greeting/com/greeting/Hello.java
```

To run the application using java executable

```
$ java --module-path HelloModularWorld/modules --module com.greeting/com.greeting.Hello
```

### Creating A JDK Class-Data Archive

The JRE already comes with a list of classes, which -Xshare:dump uses by default, so we can go straight to step 2 and generate the archive:

```
$ sudo java -Xshare:dump

narrow_klass_base = 0x0000000800000000, narrow_klass_shift = 3
Allocated temporary class space: 1073741824 bytes at 0x00000008c0000000
Allocated shared space: 3221225472 bytes at 0x0000000800000000
Loading classes to share ...
Loading classes to share: done.
Rewriting and linking classes ...
Rewriting and linking classes: done
Number of classes 1209
...
```

### Using A JDK Class-Data Archive

Just as -Xshare:dump creates the archive in a default location, -Xshare:on reads from that same default, so using the archive is pretty simple:

We can use unified logging with -Xlog to observe CDS in action by analyzing the class loading log messages:

```
$ java -Xshare:on -Xlog:class+load:file=cds.log --module-path HelloModularWorld/modules --module com.greeting/com.greeting.Hello 

Hello modular world!
```

The file cds.log then contains messages like the following:

```
$ cat cds.log   

[0.019s][info][class,load] java.lang.Object source: shared objects file
[0.019s][info][class,load] java.io.Serializable source: shared objects file
[0.019s][info][class,load] java.lang.Comparable source: shared objects file
[0.020s][info][class,load] java.lang.CharSequence source: shared objects file
...
```

As you can see, Object was loaded from the “shared objects file”, which is another term for the archive.

### Launch Time Measurements

```
$ time java -Xshare:on -Xlog:class+load:file=cds.log --module-path HelloModularWorld/modules --module com.greeting/com.greeting.Hello 

Hello modular world!
java -Xshare:on -Xlog:class+load:file=cds.log --module-path  --module   

0.27s user 
0.10s system 
106% cpu 
0.341 total
```

```
$ time java -Xshare:off -Xlog:class+load:file=cds.log --module-path HelloModularWorld/modules --module com.greeting/com.greeting.Hello 

Hello modular world!
java -Xshare:off -Xlog:class+load:file=cds.log --module-path  --module   

0.32s user 
0.08s system 
109% cpu 
0.365 total
```

Note, though, that this is the maximum performance gain you are going to get for a JDK archive. The more classes the application comes with, the lower becomes the share of JDK classes and hence the relative effect of loading them faster. To scale this effect to large applications, you need to include their classes in the archive, so let’s do that next.

## Working With An Application Class-Data Archive

### Creating A List Of Application Classes

To have the JVM create the list, run the application with the -XX:DumpLoadedClassList option:

```
$ java -XX:+UseAppCDS -XX:DumpLoadedClassList=classes.lst --module-path HelloModularWorld/modules --module com.greeting/com.greeting.Hello 

Hello modular world!
```
The JVM will then dutifully record all loaded classes. If you want to include just the classes you need to launch, exit the app right after that. If, on the other hand, you want to include classes for specific features, you should make sure they are used at least once.

```
$ cat classes.lst


java/lang/Object
java/lang/String
java/io/Serializable
java/lang/Comparable
java/lang/CharSequence
com/greeting/Hello
com/world/World
...
```

### Creating An Application Class-Data Archive

```
$ java -XX:+UseAppCDS -Xshare:dump -XX:SharedClassListFile=classes.lst  -XX:SharedArchiveFile=app-cds.jsa -Xlog:class+path=info --module-path HelloModularWorld/modules/com.greeting:HelloModularWorld/modules/com.world --module com.greeting/com.greeting.Hello

Error: non-empty directory '/Users/denismaggiorotto/Documents/sunnyvale/Academy/Courses/Java_SE_new_features/SNY.PRG.JSE.01.01.00/examples/java10/AppCDS/HelloModularWorld/modules/com.greeting/'
Error: non-empty directory '/Users/denismaggiorotto/Documents/sunnyvale/Academy/Courses/Java_SE_new_features/SNY.PRG.JSE.01.01.00/examples/java10/AppCDS/HelloModularWorld/modules/com.world/'
```
Non-empty directory error is due to the fact that AppCDS only supports classes from JAR file(s) in dump phase.

```
$ jar -c --file=HelloModularWorld/libs/com.world.jar -C HelloModularWorld/modules/com.world .
$ jar -c --file=HelloModularWorld/libs/com.greeting.jar -C HelloModularWorld/modules/com.greeting .
```

```
$ java -XX:+UseAppCDS -Xshare:dump -XX:SharedClassListFile=classes.lst  -XX:SharedArchiveFile=app-cds.jsa -Xlog:class+path=info --module-path HelloModularWorld/libs/com.greeting.jar:HelloModularWorld/libs/com.world.jar --module com.greeting/com.greeting.Hello

...
Number of classes 950
    instance classes   =   846
    obj array classes  =    96
    type array classes =     8
...
```

### Using An Application Class-Data Archive

```
$ java -Xlog:class+load:file=cds.log -XX:+UseAppCDS -Xshare:on -XX:SharedClassListFile=classes.lst  -XX:SharedArchiveFile=app-cds.jsa --module-path HelloModularWorld/libs/com.greeting.jar:HelloModularWorld/libs/com.world.jar --module com.greeting/com.greeting.Hello

Hello modular world!
```

Let's verify if the app classes were loaded form the app-cds.jsa archive

```
$ cat cds.log | egrep -ie "greet|world"                                                  

[0.131s][info][class,load] com.greeting.Hello source: shared objects file
[0.133s][info][class,load] com.world.World source: shared objects file
```

### Launch Time Measurements

```
$ time java -Xlog:class+load:file=cds.log -XX:+UseAppCDS -Xshare:on -XX:SharedClassListFile=classes.lst  -XX:SharedArchiveFile=app-cds.jsa --module-path HelloModularWorld/libs/com.greeting.jar:HelloModularWorld/libs/com.world.jar --module com.greeting/com.greeting.Hello

Hello modular world!

0.22s user 
0.09s system 
101% cpu 
0.298 total
```

```
$ time java -Xlog:class+load:file=cds.log -XX:+UseAppCDS -Xshare:off -XX:SharedClassListFile=classes.lst  -XX:SharedArchiveFile=app-cds.jsa --module-path HelloModularWorld/libs/com.greeting.jar:HelloModularWorld/libs/com.world.jar --module com.greeting/com.greeting.Hello

0.32s user 
0.08s system 
110% cpu 
0.359 total
```

For a simple “Hello, World” application there is of course no performance boost of AppCDS over CDS because loading one class more or less from the archive has no measurable impact, but still... the use of -Xshare:on boosts the startup of the JVM a bit.

