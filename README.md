# L'âge de Psyché

## Description

   L'âge de Psyché est un jeu de plateau qui se joue à deux joueurs. Le but du jeu est de récupérer des ressources sur le plateau ainsi que des mines afin de gagner des points. Le jeu se termine lorsque toutes les mines ont été conquises.
   Ce jeu est un jeu à thème variable, c'est à dire que le thème du jeu peut être modifié en fonction des envies des joueurs. Dans notre cas, le thème est basé sur le futur et la conquète d'un astéroide, mais il est possible de changer le thème selon les envies.

## Table des matières

- [Installation](#installation)
  - [1. Décompresser l'archive](#1-décompresser-larchive)
  - [2. Contenu de l'archive](#2-contenu-de-larchive)
  - [3. Lancer l'application avec les scripts run](#3-lancer-lapplication-avec-les-scripts-run)
    - [a. Sous Linux](#a-sous-linux)
    - [b. Sous Windows](#b-sous-windows)
  - [4. Lancer l'application depuis le terminal (sans script)](#4-lancer-lapplication-depuis-le-terminal-sans-script)
- [Auteurs](#auteurs)

## Installation
### 1. Décompresser l'archive
   Décompressez l'archive dans un dossier de votre choix, puis ouvrez le dossier L'age_de_Psyche.

### 2. Contenu de l'archive

   - Dossier src   : contient les fichiers sources du jeu.
   - Dossier bin   : contient les fichiers compilés du jeu.
   - Dossier theme : contient les images du jeu ainsi que le thème
   - README.md     : fichier contenant les informations sur le jeu.
   - run.sh        : script de lancement du jeu sous Linux.
   - run.bat       : script de lancement du jeu sous Windows.


   Le fichier compile.list se trouve dans le dossier src.

### 3. Lancer l'application avec les scripts run

   a. Sous Linux
   ```bash
   chmod u + x run.sh
   ./run.sh
   ```

   b. Sous Windows
   ```bash
   run.bat
   ```

   Vous pouvez également lancer l'application avec un double clic sur le fichier run.sh ou run.bat.
   Une fois lancé, ce script vous demandera de choisir un scénario. Pour lancer le jeu sans scénario, il faudra rentrer "4" dans le terminal.

### 4. Lancer l'application depuis le terminal (sans script)

   Pour lancer l'application sans utiliser les scripts existants, utilisez les commandes suivantes (sous Windows ou Linux) :
   ```bash
   cd src
   javac @compile.list -d ../bin

   cd ../bin
   java Psyche [nom du scénario]
   ```
   [nom du scénario] est à remplacer par le scénario que vous souhaitez (scenario_1.run, scenario_2.run, scenario_3.run).
   Pour lancer le jeu sans scénario, ne rentrez rien après "java Psyche".

## Auteurs

Groupe B3 - C1 :

- **Cnaepelnickx Evan**
- **Laparre Quentin**
- **Lopez Antonin**
- **Debein Rafael**
- **Herambert Ted**
