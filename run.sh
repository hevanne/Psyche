#!/bin/bash

# Compilation
cd src

javac @compile.list -d bin

# Execution
cd ../bin

java Psyche

read -p "Press any key to continue..."
