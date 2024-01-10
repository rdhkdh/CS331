### Ridhiman Dhindsa, 210101088, CS331 lab 1
# Q1 - Assignment Ques 2
Incrementing (steps of 1) and decrementing (steps of 1) a shared counter by using locks as synchronization 
mechanisms. Value of counter or step amount can be changed in source code if required.  
### Eg output with +1, -2 step:  
Initial counter value = 100

Counter value (thread 1)= 101  
Counter value (thread 2)= 99  
Counter value (thread 1)= 100  
Counter value (thread 2)= 98  
Counter value (thread 1)= 99  
Counter value (thread 2)= 97  
Counter value (thread 1)= 98  
Counter value (thread 2)= 96  
Counter value (thread 1)= 97  
Counter value (thread 2)= 95  

Final counter value = 95

### Eg output with +1, -1 step:  
Initial counter value = 100

Counter value (thread 1)= 101  
Counter value (thread 2)= 100  
Counter value (thread 1)= 101  
Counter value (thread 2)= 100  
Counter value (thread 1)= 101  
Counter value (thread 2)= 100  
Counter value (thread 1)= 101  
Counter value (thread 2)= 100  
Counter value (thread 1)= 101  
Counter value (thread 2)= 100  

Final counter value = 100

# Q2 - Assignment Ques 5
Matrices A, B are generated randomly, and then multiplied, where each (row X col) pair is calculated by a 
single thread. Therefore NxN matrix will be calculated by N threads.