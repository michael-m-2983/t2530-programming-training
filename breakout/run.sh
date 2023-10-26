# Create the bin directory if it doesn't already exist.
mkdir -p bin

# Compile
javac -d bin/ -sourcepath src/ src/breakout/Main.java

# Run
java -cp bin/ breakout.Main
