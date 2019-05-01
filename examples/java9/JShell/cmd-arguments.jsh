// Pass arguments via system properties
// Usage: jshell -v -R-Dfile=test.txt cmd-arguments.jsh

String fileName = System.getProperty("file")
System.out.println("Input file name is: " + fileName)

/exit