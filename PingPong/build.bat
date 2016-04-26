@echo off

echo Manifest-Version: 1.0 > Manifest.mf
echo Main-Class: start.StartGame >> Manifest.mf
echo Generated Manifest file

cd src

javac start/StartGame.java
echo Complied source to class files

jar cfm ..\PingPong.jar ..\Manifest.mf *
echo Created PingPong.jar

del ..\Manifest.mf
echo Remove Manifest file


echo DONE
pause > nul