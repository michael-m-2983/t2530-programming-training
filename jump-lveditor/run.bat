if not exist bin mkdir bin
javac -d bin/ -sourcepath src/ src/lveditor/Main.java
java -cp bin/ lveditor.Main 