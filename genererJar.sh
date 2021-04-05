#!/bin/bash
[[ -d jar ]] && rm -r jar
mkdir jar
javac -d jar src/*/*.java
javadoc -package -d doc src/*/*.java
cp -R src/Resources jar/Resources
cd jar
printf "Manifest-Version: 1.0\nMain-Class: main.MainClass\n" >app.mf
jar cmf app.mf Labyrinthe.jar */*.class */Resources/*/*
java -jar Labyrinthe.jar
