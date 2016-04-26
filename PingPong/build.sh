#/bin/bash

javac="javac"
jar="jar"
mf="Manifest.mf"

echo "Manifest-Version: 1.0" > "$mf"
echo "Main-Class: start.StartGame" >> "$mf"
echo "Generated Manifest file"

cd src
$javac start/StartGame.java
echo "Complied source to class files"

$jar cfm "../PingPong.jar" "../$mf" *
echo "Created PingPong.jar"

rm "../$mf"
echo "Remove Manifest file"
echo "DONE"