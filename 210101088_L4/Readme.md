# Lab 4 CS331
Ridhiman Kaur Dhindsa, 210101088  
Date: 31 Jan 2024  

## Steps to run
1) Open terminal in directory containing the file.  
2) Run the command `java WebServer.java`  
3) Press Ctrl+C to exit.  

## Description
Created a busy web server handling mechanism with requests having 3 priorities- 1 (highest), 2, 3 (lowest). The requests are generated randomly with 1 of the 3 priorities and processing time in [0,3]. T threads are used to simulate T clients (or request generators),where each thread generates 3 req/sec. Therefore **arrival rate** of requests = 3T/sec. The server does **weighted fair queue (WFQ) scheduling** by giving more time to higher priority requests. 3 queues (q1,q2,q3) are maintained for the 3 priorities and allotted time in the ratio 3:2:1. Meaning, priority 1 queue is given 3 times more time on server than other priorities. Similarly, priority 2 queue is given 2 times more time than priority 3 queue.  
The following are the classes used:    

1) `Request(int priority, double processing_time)`  
It is the template for a web request from the client.    

2) `ReqGen(int index)`  
It simulates request generation by a client. T request generators are created. T can be modified in the code if required. This eventually controls the arrival rate.  

3) `Server(int lag)`  
Applies WFQ scheduling and checks the queues by priority. Serves them in the ratio 3:2:1. Therfore serves 6 requests in `lag` number of seconds. The `lag` input can be changed and hence used to control service rate. Calculates server uptime as well.   
> NOTE: If service rate is increased, we can see the effect of lesser pending requests.   

4) `Q2Server()`  
Gives pending status update and calculates average queue length for each priority every 5 sec.  