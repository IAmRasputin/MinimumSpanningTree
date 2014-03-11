all:
	javac -d bin src/*.java

run:
	javac -d bin src/*.java
	java -cp bin MST testing/input1

clean:
	rm bin/*
