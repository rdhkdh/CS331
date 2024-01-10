//Ridhiman Kaur Dhindsa, 210101088
package lab1;

class Counter {
    //global variables
    public static int[] counter = {100}; //pass by reference
    public static Object lock = new Object();
    
    static class Increment extends Thread {
        private int[] counter;
        private Object lock;

        // Constructor to receive the counter value
        public Increment(int[] counter, Object lock) {
            this.counter = counter;
            this.lock = lock;
        }

        public void run() {
            //System.out.println("Thread 1");
            for(int i=1;i<=5;i++)
            {
                try{Thread.sleep(600);}
                catch(InterruptedException e) {e.printStackTrace();}

                synchronized (lock) 
                {
                    counter[0]++;
                    System.out.printf("Counter value (thread 1)= %d\n",counter[0]);
                    lock.notifyAll();

                    if(i<=4)
                    {
                        try{lock.wait();}
                        catch(InterruptedException e) {e.printStackTrace();}
                    }
                }
            }
        }
    }

    static class Decrement extends Thread {
        private int[] counter;
        private Object lock;

        // Constructor to receive the counter value
        public Decrement(int[] counter, Object lock) {
            this.counter = counter;
            this.lock = lock;
        }

        public void run() {
            //System.out.println("Thread 2");
            for(int i=1;i<=5;i++)
            {
                try{Thread.sleep(2000);}
                catch(InterruptedException e) {e.printStackTrace();}

                synchronized(lock) {
                    counter[0]--;
                    System.out.printf("Counter value (thread 2)= %d\n",counter[0]);
                    lock.notifyAll();

                    if(i<=4)
                    {
                        try{lock.wait();}
                        catch(InterruptedException e) {e.printStackTrace();}
                    }
                    
                }
            }
        }
    }

    public static void main(String args[]) {
        System.out.printf("Initial counter value = %d\n\n",counter[0]);

        Increment t1 = new Increment(counter, lock);
        Decrement t2 = new Decrement(counter, lock);

        t1.start();
        t2.start();

        try {
            // Wait for t1 and t2 to complete
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("\nFinal counter value = %d\n",counter[0]);
    }
}