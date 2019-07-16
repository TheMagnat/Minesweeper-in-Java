CC=javac
START=java

jeu:
	$(CC) Jeu.java

start:
	$(START) Jeu

clean:
	rm *.class