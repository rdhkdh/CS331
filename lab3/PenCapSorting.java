package lab3;

// Ridhiman Kaur Dhindsa, 210101088

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class PenCapSorting {
    //global variables
    public static List<String> pens = new ArrayList<>(); //pending pens
    public static List<String> caps = new ArrayList<>(); //pending caps
    public static List<String> done = new ArrayList<>(); //pairs which are matched

    // picks pens/caps from production line after every 1 sec
    static class RoboticArm extends Thread {
        private int index;
        String colours[] = {"Red", "Blue", "Green", "Black"};

        //constructor
        public RoboticArm(int index1)
        {
            this.index = index1;
        }

        public void run()
        {
            Random rand = new Random();
            int x;

            while(true)
            {
                try{Thread.sleep(1000);}
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                x = rand.nextInt(4);
                pens.add(colours[x]); //add to peniding list in matching machine
                System.out.printf("Robot arm %d: picked pen of colour %s\n", index, colours[x]);

                x = rand.nextInt(4);
                caps.add(colours[x]); //add to pending list in matching machine
                System.out.printf("Robot arm %d: picked cap of colour %s\n", index, colours[x]);
            }
        }
    }

    // matches pairs from pending lists
    static class MatchingMachine extends Thread {
        public void run()
        {
            while(true)
            {
                synchronized (pens) {
                    if (pens.isEmpty()) {
                        continue; // Wait if no pens available
                    }
                }
    
                synchronized (caps) {
                    if (caps.isEmpty()) {
                        continue; // Wait if no caps available
                    }
                }
    
                String p_color = pens.get(0);
                String c_color;
                for(int i=0;i<caps.size();i++)
                {
                    c_color = caps.get(i);
                    if(c_color.equals(p_color))
                    {
                        caps.remove(i);
                        pens.remove(0);
                        done.add(p_color);
                        System.out.printf("Matching Machine: Pair of colour %s matched.\n", p_color);
                        break;
                    }
                }
            }
        }
    }

    // gives status count of each color on shelf, after every 5s
    static class ShelfManager extends Thread{
        public void run()
        {
            while(true)
            {
                try{Thread.sleep(5000);}
                catch(InterruptedException e) {e.printStackTrace();}

                int red=0,blue=0,green=0,black=0;

                System.out.printf("Shelf Manager: ");
                for(int i=0; i<done.size(); i++)
                {
                    if(done.get(i)=="Red") {red++;}
                    if(done.get(i)=="Blue") {blue++;}
                    if(done.get(i)=="Green") {green++;}
                    if(done.get(i)=="Black") {black++;}
                    
                }
                System.out.printf("Red: %d, Blue: %d, Green: %d, Black: %d\n",red,blue,green,black);
            }
        }
    }

    public static void main(String args[])
    {
        int N = 2; // no. of robotic arms

        System.out.println("Press Ctrl+C to exit.");
        System.out.printf("Starting program with %d Robotic arms.\n\n", N);

        for(int i=1;i<=N;i++) // start N robotic arms
        {
            RoboticArm r = new RoboticArm(i);
            r.start();
        }
        
        MatchingMachine m = new MatchingMachine();
        m.start();

        ShelfManager sm = new ShelfManager();
        sm.start();
    }
}
