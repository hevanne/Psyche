@echo off

REM Compilation
cd src

javac @compile.list -d ../bin

REM Exécution
cd ../bin

java Psyche

pause