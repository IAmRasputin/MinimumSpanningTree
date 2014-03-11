all:
	-mkdir -p bin
	javac -d bin src/*.java

run:
	-mkdir -p bin
	javac -d bin src/*.java
	java -cp bin MST testing/input1

clean:
	-mkdir -p bin
	rm bin/*
