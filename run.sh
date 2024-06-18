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

while [ $scenario -ne 1 ] && [ $scenario -ne 2 ] && [ $scenario -ne 3 ] && [ $scenario -ne 4 ]
do
	echo "Choix invalide"
	read -p "Choix : " scenario
done

if [ $scenario -eq 1 ]
then
	java Psyche scenario1
elif [ $scenario -eq 2 ]
then
	java Psyche scenario2
elif [ $scenario -eq 3 ]
then
	java Psyche scenario3
elif [ $scenario -eq 4 ]
then
	java Psyche
else
	echo "Choix invalide"
fi

read -p "Press any key to continue..."