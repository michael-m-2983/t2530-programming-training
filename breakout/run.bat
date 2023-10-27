if not exist bin mkdir bin
javac -d bin/ -sourcepath src/ src/breakout/Main.java
java -cp bin/ breakout.Main