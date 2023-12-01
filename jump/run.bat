if not exist bin mkdir bin
javac -d bin/ -sourcepath src/ src/jump/Main.java
java -cp bin/ jump.Main 