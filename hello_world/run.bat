if not exist bin mkdir bin
javac -d bin/ -sourcepath src/ src/hello/Main.java
java -cp bin/ hello.Main