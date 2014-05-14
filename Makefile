all:
	-mkdir -p bin
	javac -d bin src/*.java

run:
	-mkdir -p bin
	javac -d bin src/*.java
	java -cp bin MST testing/input1

analyze:
	-mkdir -p bin
	javac -d bin src/*.java
	java -cp bin -agentlib:hprof=cpu=samples MST testing/input1

clean:
	-mkdir -p bin
	rm bin/*
