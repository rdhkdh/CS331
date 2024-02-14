package lab1;

class Increment extends Thread {
    private int[] counter;

    // Constructor to receive the counter value
    public Increment(int[] counter) {
        this.counter = counter;
    }

    public void run() {
        synchronized (counter) {
            System.out.println("Thread 1");
            for(int i=1;i<=5;i++)
            {
                if(i>3)
                {
                    try {
                    // Sleep for 1 second (1000 milliseconds)
                    Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                counter[0]++;
                System.out.printf("Counter value (thread 1)= %d\n\n",counter[0]);
            }
        }
    }
}

class . extends Thread {
    private int[] counter;

    // Constructor to receive the counter value
    public Decrement(int[] counter) {
        this.counter = counter;
    }

    public void run() {
        synchronized(counter) {
            System.out.println("Thread 2");
            for(int i=1;i<=5;i++)
            {
                counter[0] -= 2;
                System.out.printf("Counter value (thread 2)= %d\n\n",counter[0]);
            }
        }
    }
}

class ques2 {


    public static int[] counter = {100}; 
    

    public static void main(String args[]) {
        System.out.printf("Initial counter value = %d\n",counter[0]);

        Increment t1 = new Increment(counter);
        Decrement t2 = new Decrement(counter);

        t1.start();
        t2.start();

        try {
            // Wait for t1 and t2 to complete
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Final counter value = %d\n",counter[0]);
    }
}