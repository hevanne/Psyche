@echo off

REM Compilation
cd src

javac @compile.list -d ../bin

REM Exécution
cd ../bin

echo Choisissez un scenario :
echo 1 - Scenario 1
echo 2 - Scenario 2
echo 3 - Scenario 3

set /p scenario=Choix :

if %scenario%==1 (
	java Psyche scenario1
)

if %scenario%==2 (
	java Psyche scenario2
)

if %scenario%==3 (

pause