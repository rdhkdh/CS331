# CS331 Lab 2
> Ridhiman Kaur Dhindsa, 210101088

## Steps to run:
1) Open terminal in directory containing the file.  
2) Run the following command: `java TrafficSignal.java`
3) Press Ctrl+C to exit.  

## Description:
Created a traffic light controller in Java which uses the following classes:   
1) `RedLight()`  
Inputs: none  
Outputs: prints 'Red' light, waits for 10 sec.  

2) `YellowLight()`  
Inputs: none  
Outputs: prints 'Yellow' light, waits for 3 sec.  

3) `GreenLight()`  
Inputs: none  
Outputs: prints 'Green' light, waits for 15 sec.  

4) `Signal(int index)`  
Inputs: index- an integer  
Outputs: Creates an instance of 3 threads - Red, Yellow, Green light in order, and waits for them to join. Runs this cycle indefinitely.

5) `main()`  
Inputs: n- number of traffic lights. User can define the number of traffic lights they want to create.  
lag- Time gap beween consecutive instances of traffic lights.  
Outputs: Concurrently runs 'n' traffic lights. Each instance is created with a lag of 500ms. User can change that value. 
