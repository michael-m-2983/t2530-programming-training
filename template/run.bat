if not exist bin mkdir bin
javac -d bin/ -sourcepath src/ src/template/Main.java
java -cp bin/ template.Main 