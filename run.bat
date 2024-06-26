@echo off

REM Compilation
cd src

javac @compile.list -d ../bin

REM Exécution
cd ../bin

:choix
echo Choisissez un scenario :
echo 1 - Scenario 1
echo 2 - Scenario 2
echo 3 - Scenario 3
echo 4 - Jeu sans scenario

set /p scenario=Choix : 
if not defined scenario goto choix

if %scenario%==1 (
	java Psyche scenario_1.run
	goto fin
)

if %scenario%==2 (
	java Psyche scenario_2.run
	goto fin
)

if %scenario%==3 (
	java Psyche scenario_3.run
	goto fin
)

if %scenario%==4 (
	java Psyche
	goto fin
)

echo Choix invalide
goto choix

:fin

pause