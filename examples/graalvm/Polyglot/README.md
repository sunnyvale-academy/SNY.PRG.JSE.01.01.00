# Polyglot JDK


Setup the GraalVM JAVA_HOME and PATH variables

```
$ export JAVA_HOME=/path/to/GraalVM/Home # ie: export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-ee-19.0.0/Contents/Home
$ export PATH=$JAVA_HOME/bin:$PATH

java version "1.8.0_212"
Java(TM) SE Runtime Environment (build 1.8.0_212-b31)
Java HotSpot(TM) GraalVM EE 19.0.0 (build 25.212-b31-jvmci-19-b01, mixed mode)
```




```
$ mkdir -p HelloPolyglotWorld/target/classes  
$ javac HelloPolyglotWorld/src/main/java/it/sunnyvale/academy/jsenewfeatures/polyglot/HelloPolyglotWorld.java -d HelloPolyglotWorld/target/classes
```

```
$ java -cp HelloPolyglotWorld/target/classes  it.sunnyvale.academy.jsenewfeatures.polyglot.HelloPolyglotWorld

Hello polyglot world Java!
Hello polyglot world JavaScript!
Exception in thread "main" java.lang.IllegalArgumentException: A language with id 'ruby' is not installed. Installed languages are: [js, llvm].
        at com.oracle.truffle.polyglot.PolyglotEngineImpl.requirePublicLanguage(PolyglotEngineImpl.java:695)
        at com.oracle.truffle.polyglot.PolyglotContextImpl.requirePublicLanguage(PolyglotContextImpl.java:821)
        at com.oracle.truffle.polyglot.PolyglotContextImpl.eval(PolyglotContextImpl.java:792)
        at org.graalvm.polyglot.Context.eval(Context.java:341)
        at org.graalvm.polyglot.Context.eval(Context.java:367)
        at it.sunnyvale.academy.jsenewfeatures.polyglot.HelloPolyglotWorld.main(HelloPolyglotWorld.java:21)
```

```
$ gu install ruby

Skipping ULN EE channels, no username provided.
Downloading: Component catalog from www.graalvm.org
Downloading: Component catalog from www.graalvm.org
Processing component archive: TruffleRuby
Downloading: Component ruby: TruffleRuby  from github.com
Installing new component: TruffleRuby (org.graalvm.ruby, version 19.0.0)

IMPORTANT NOTE:
---------------
The Ruby openssl C extension needs to be recompiled on your system to work with the installed libssl.
First, make sure TruffleRuby's dependencies are installed, which are described at:
  https://github.com/oracle/truffleruby/blob/master/README.md#dependencies
Then run the following command:
        /Library/Java/JavaVirtualMachines/graalvm-ee-19.0.0/Contents/Home/jre/languages/ruby/lib/truffle/post_install_hook.sh


IMPORTANT NOTE:
---------------
Set of GraalVM components that provide language implementations have changed. The Polyglot native image and polyglot native C library may be out of sync: 
- new languages may not be accessible
- removed languages may cause the native binary to fail on missing resources or libraries.
To rebuild and refresh the native binaries, use the following command:
        /Library/Java/JavaVirtualMachines/graalvm-ee-19.0.0/Contents/Home/bin/gu rebuild-images
```

Install additional language runtimes

```
$ gu install R
...
$ gu install python
...
```


```
$ java -cp HelloPolyglotWorld/target/classes  it.sunnyvale.academy.jsenewfeatures.polyglot.HelloPolyglotWorld

Hello polyglot world Java!
Hello polyglot world JavaScript!
Hello polyglot world Ruby!
FastR unexpected failure: error loading libR from: /Library/Java/JavaVirtualMachines/graalvm-ee-19.0.0/Contents/Home/jre/languages/R/lib/libR.dylib.
If running on the NFI backend, did you provide the location of libtrufflenfi.so as the value of the system property 'truffle.nfi.library'?
The current value is 'null'.
Is the OpenMP runtime library (libgomp.so) present on your system? This library is, e.g., typically part of the GCC package.
Details: Access to native code is not allowed by the host environment.
Exception in thread "main" org.graalvm.polyglot.PolyglotException
        at org.graalvm.polyglot.Context.eval(Context.java:367)
        at it.sunnyvale.academy.jsenewfeatures.polyglot.HelloPolyglotWorld.main(HelloPolyglotWorld.java:22)
```

The error "Access to native code is not allowed by the host environment" is due to the following: Context provides an execution environment for guest languages, R and some other languages require the allowAllAccess flag to be set to true.

Substitue the following line in HelloPolyglotWorld.java

```
 Context context = Context.create();
```
with:
```
Context.newBuilder().allowNativeAccess(true).build();
```

and re-compile/run the application

```
$ javac HelloPolyglotWorld/src/main/java/it/sunnyvale/academy/jsenewfeatures/polyglot/HelloPolyglotWorld.java -d HelloPolyglotWorld/target/classes 

$ java -cp HelloPolyglotWorld/target/classes  it.sunnyvale.academy.jsenewfeatures.polyglot.HelloPolyglotWorld

...
Original Internal Error: 
java.lang.SecurityException: Operation is not allowed for: /var/folders/4d/_r283cnd6r5g7qpqgrnt2j1m0000gp/T
        at com.oracle.truffle.polyglot.FileSystems.forbidden(FileSystems.java:983)
        at com.oracle.truffle.polyglot.FileSystems.access$900(FileSystems.java:79)
        at com.oracle.truffle.polyglot.FileSystems$DeniedIOFileSystem.checkAccess(FileSystems.java:747)
        at com.oracle.truffle.api.TruffleFile.checkAccess(TruffleFile.java:1867)
```

Now we get an error by the underlying operating system (Mac OS in this case).
So let's try with super user permissions

```
$  sudo java -cp HelloPolyglotWorld/target/classes  it.sunnyvale.academy.jsenewfeatures.polyglot.HelloPolyglotWorld                         

Password:
Hello polyglot world Java!
Hello polyglot world JavaScript!
Hello polyglot world Ruby!
[1] "Hello polyglot world R!"
Hello polyglot world Python!
```





