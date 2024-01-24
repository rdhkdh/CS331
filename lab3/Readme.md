# Lab 3 CS331
Ridhiman Kaur Dhindsa, 210101088  
Date: 24 Jan 2024  

## Steps to run
1) Open terminal in directory containing the file.  
2) Run the command `java PenCapSorting.java`  
3) Press Ctrl+C to exit.  

## Description
Create a pen cap sorting machine with the following classes:  
1) `RoboticArm(int index)`  
`index` represents the index number of the robotic arm. 
It picks pens and caps of different colors. The colors are generated randomly from the set- red, blue, green, black afer every 1 sec. These are picked and added to the lsit of pending pens/caps for matching machine to operate on.  

2) `MatchingMachine(int index)`  
Picks pens and caps from pending list and matches them. Then sends the pair to shelf manager.  

3) `ShelfManager()`  
Prints the number of matched pairs of each color, after every 5 seconds.  

4) `main()`  
Inputs- N: number of robotic arms  
Outputs: Runs all robotic arms, machine manager and shelf manager and displays their outputs.