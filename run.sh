#!/bin/bash

javac @compile.list -d bin

cd bin

java Controleur

read -p "Press any key to continue..."