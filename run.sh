#!/bin/bash

# Compilation
cd src

javac @compile.list -d ../bin

# Execution
cd ../bin

echo "Choisissez votre scenario : (entrez le numero correspondant au scenario)"
echo "1. Scenario 1"
echo "2. Scenario 2"
echo "3. Scenario 3"
echo "4. Jeu sans scenario"

read -p "Choix : " scenario

while ! [[ $scenario =~ ^[0-9]+$ ]] || [ $scenario -lt 1 ] || [ $scenario -gt 4 ]
do
	echo "Choix invalide"
	read -p "Choix : " scenario
done

if [ $scenario -eq 1 ]
then
	java Psyche scenario_1.run
elif [ $scenario -eq 2 ]
then
	java Psyche scenario_2.run
elif [ $scenario -eq 3 ]
then
	java Psyche scenario_3.run
elif [ $scenario -eq 4 ]
then
	java Psyche
else
	echo "Choix invalide"
fi

read -p "Press any key to continue..."
