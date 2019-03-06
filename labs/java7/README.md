# Java SE 7 labs

## New File I/O API (NIO.2) 
### Directory comparator tool

In this exercise you will create a tool which, given two directories, will recursively compare the contents of those directories, and return lists of files which are in one directory but not the other. You will use **File**, **Path**, and **FileVisitor** classes, among others.

Create a class CompareDirectories with the following method:

```
public static List<Path>[] compareDirectories(Path oldDir, Path newDir) throws IOException
```


## ForkJoin framework
### String case conversion using ForkJoin framework (RecursiveAction)

In this exercise you will create a tool which, given a String of lowercase charachters, will recursively process every characther transforming it to uppercase.


Create a class named **ForkJoinUppercaseAction** that extends RecursiveAction, this class have to work in the following way:

```
char[] charArray = new char[4096];
charArray = fillArray(charArray);
String workload = new String(charArray);
ForkJoinUppercaseAction forkJoinUppercase = new ForkJoinUppercaseAction(workload, 100);
forkJoinUppercase.invoke();
```


### String case conversion using ForkJoin framework (RecursiveTask)

Create a class named **ForkJoinUppercaseTask** that extends RecursiveTask, this class have to work in the following way:

```
char[] charArray = new char[4096];
charArray = fillArray(charArray);
String workload = new String(charArray);
ForkJoinUppercaseTask forkJoinUppercase = new ForkJoinUppercaseTask(workload, 100);
String result = forkJoinUppercase.invoke();
```
### Apply GC monitoring techniques with java2d demo application
Please refer to [java2d](java2d/README.md)

### Use G1 GC algorithm with java2d demo application
Please refer to [java2d](java2d/README.md)