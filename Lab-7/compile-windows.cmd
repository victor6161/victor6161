rmdir -rf bin

mkdir bin

"%JAVA_HOME%/bin/javac" -d bin -sourcepath src -cp libs/* src/by/bsu/up/chat/*.java