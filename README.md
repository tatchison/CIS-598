# CIS-598

Travis Atchison 
Kansas State Universtiy, Spring 2018
CIS 598 Senior Project

Genetic Programming for RoboCode
Developed in ECJ

This solution was developed in ECJ version 24. 

Once you download ECJ, add the path to ecj in your CLASSPATH.
Then, add the folder robocode to ecj/ec/app, then in the command line
run the command "java ec.Evolve -file robocode.params" while in the 
robocode directory.

Note: This solution uses an indirect evaluation method, meaning that it outputs the decision trees
of each individual and requires the user to translate and run them in RoboCode. The template for the 
robot is in CIS598GPRobot.java, with the methods moveTree and fireTree being the location for each of the 
two trees. An output file is written, but you can either rename and relocate this file to the input directory, 
or adjust the robot source code to write directly to the file.
