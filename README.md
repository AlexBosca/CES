## Component Definer And Static Analyzer
This project takes an input file that contains all the classes in a project (the full name of these <modem_nameul> / <package_name> / <class_name> and extracts the existing components from it

## Features
This project takes an input file that contains all the classes in a project (the full name of these <modem_nameul> / <package_name> / <class_name> and extracts the existing components from it

## Installation
 ####Installation from Git Release
  To use the plugin and run it on the local machine it is necessary to perform the steps below:<br>
   1. Download the zip archive from the code button
   2. Unzip archive
   3. Make sure you have the maven tool installed and you use java 1.8 <br>
     Follow these steps to install maven https://www.baeldung.com/install-maven-on-windows-linux-mac <br>
     Follow these steps to install java if necessarily https://java.com/en/download/help/download_options.html <br>
   
## How to use?
   Follow these steps after installation part
   1. Run maven clean install in order to obtain a packed jar from given class
   2. Create an input txt file which is input for the jar and a result.json file which represents the output<br>
   3. Open a command promt window
   4. Run command **java -jar gradle-component-definer.jar outputFile inputFile**<br>
    **it is very important not to change the order of the parameters because the file will overwrite the input file with an empty file, ie the output one**


