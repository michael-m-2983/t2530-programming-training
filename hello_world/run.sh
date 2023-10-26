# Create the bin directory if it doesn't already exist.
mkdir -p bin

# Compile
javac -d bin/ -sourcepath src/ src/hello/Main.java

# Run
java -cp bin/ hello.Main