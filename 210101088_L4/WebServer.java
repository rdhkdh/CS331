//Ridhiman Dhindsa, 210101088

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class WebServer{
    //global variables
    public static int T = 2; //no of threads

    //template for client request to be sent
    static class Request{
        private int priority;
        private double processing_time;

        //constructor
        public Request(int priority1, double processing_time1)
        {
            this.priority = priority1;
            this.processing_time = processing_time1;
        }

        public int getPriority() {
            return priority;
        }
        public double getProcessingTime() {
            return processing_time;
        }
    }

    //global variables
    public static Queue<Request> q1 = new LinkedList<> (); //queue for priority 1 requests
    public static Queue<Request> q2 = new LinkedList<> (); //queue for priority 2 requests
    public static Queue<Request> q3 = new LinkedList<> (); //queue for priority 3 requests

    // generates 3 req per sec. T threads will generate 3T req per sec.
    static class ReqGen extends Thread{
        private int index;

        //constructor
        public ReqGen(int index1)
        {
            this.index = index1;
        }

        public void run()
        {
            Random rand = new Random();
            int prio = rand.nextInt(3)+1;    //priority= 1>2>3
            double proc_time = rand.nextDouble()*3.0;     //time = [0,3]

            while(true)
            {
                try{Thread.sleep(1000);} //each thread generates 3 req per sec. Total rate = 3T/sec
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                for(int i=1;i<=3;i++) //generate 3 requests
                {
                    prio = rand.nextInt(3)+1; 
                    proc_time = rand.nextDouble()*3.0; 
                    Request r = new Request(prio,proc_time);

                    if(prio==1)
                    { //push into appropriate queue
                        q1.offer(r);
                    }
                    else if(prio==2)
                    {
                        q2.offer(r);
                    }
                    else if(prio==3)
                    {
                        q3.offer(r);
                    }

                    System.out.printf("Request generator %d: priority=%d, processing time=%f\n", index, prio, proc_time);
                }
                
            }
        }
    }

    // serves 6 requests per 'lag' number of seconds
    static class Server extends Thread{ 
        private int lag; //lag in milliseconds

        //constructor
        public Server(int x)
        {
            this.lag = x;
        }

        public void run()
        {
            double uptime=0; //server uptime

            while(true)
            {
                try{Thread.sleep(lag);}
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                int j=1;
                for(int i=1;i<=6;i++)
                {
                    if(j<=3) //serve priority 1, for j=1,2,3
                    {
                        if(q1.isEmpty()) //if queue is empty, continue to next request in other queue
                        {   
                            j++; 
                            continue;
                        }

                        Request r1 = q1.poll(); 
                        uptime += r1.processing_time; //add to uptime
                        System.out.printf("Web Server: Request served. Priority=%d Processing time=%f Server uptime=%f\n", r1.priority, r1.processing_time, uptime);
                    }
                    else if(j==4 || j==5) //serve priority 2, for j=4,5
                    {
                        if(q2.isEmpty()) 
                        {
                            j++;
                            continue;
                        }

                        Request r2 = q2.poll();
                        uptime += r2.processing_time;
                        System.out.printf("Web Server: Request served. Priority=%d Processing time=%f Server uptime=%f\n", r2.priority, r2.processing_time, uptime);
                    }
                    else if(j==6) //serve priority 3, for j=6
                    {
                        if(q3.isEmpty()) 
                        {
                            j=1;
                            continue;
                        }

                        Request r3 = q3.poll();
                        uptime += r3.processing_time;
                        System.out.printf("Web Server: Request served. Priority=%d Processing time=%f Server uptime=%f\n", r3.priority, r3.processing_time, uptime);
                    }

                    //increment j
                    if(j==6) {j=1;}
                    else{ j++; }
                }
            }
        }
    }

    // gives status of queues periodically every 5 sec, also calculates avg queue length
    static class Q2Server extends Thread{
        public void run()
        {
            double time=0;
            double l1=0,l2=0,l3=0;
            while(true)
            {
                try{Thread.sleep(5000);}
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                time += 5.0; //total time elapsed
                l1 += q1.size(); //total queue length of all data points
                l2 += q2.size();
                l3 += q3.size();
                double x1 = l1/time; //avg queue length
                double x2 = l2/time;
                double x3 = l3/time;

                System.out.printf("Q2Server: Pending requests:\n");
                System.out.printf("Priority 1= %d, Avg Queue length = %f\n", q1.size(), x1);
                System.out.printf("Priority 2= %d, Avg Queue length = %f\n", q2.size(), x2);
                System.out.printf("Priority 3= %d, Avg Queue length = %f\n\n", q3.size(), x3);
            }
        }
    }

    public static void main(String args[])
    {
        System.out.println("Press Ctrl+C to exit.");
        System.out.printf("Starting program with %d Request Generators.\n\n", T);


        for(int i=1;i<=T;i++) // start T request generators
        {
            ReqGen r = new ReqGen(i);
            r.start();
        }
        
        Server m = new Server(3000); // lag= 3 sec
        m.start();

        Q2Server s = new Q2Server();
        s.start();
    }

}