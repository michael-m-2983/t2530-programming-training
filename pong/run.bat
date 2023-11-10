if not exist bin mkdir bin
javac -d bin/ -sourcepath src/ src/pong/Main.java
java -cp bin/ pong.Main 