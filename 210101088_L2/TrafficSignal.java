// Ridhiman Kaur Dhindsa, 210101088

public class TrafficSignal {
    // global variables
    public static int n = 2; // number of traffic lights
    public static int lag = 500; // lag in milliseconds

    // prints Red colour light and waits for 10 seconds
    static class RedLight extends Thread {
        public void run() {
            System.out.println("Red");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // prints Yellow colour light and waits for 3 seconds
    static class YellowLight extends Thread {
        public void run() {
            System.out.println("Yellow");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // prints Green colour light and waits for 15 seconds
    static class GreenLight extends Thread {
        public void run() {
            System.out.println("Green");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Carries out the cycle: red->green->yellow->red indefinitely
    static class Signal extends Thread {
        private int index; // index of the traffic light instance

        // constructor
        public Signal(int index1) {
            this.index = index1;
        }

        public void run() {
            while (true) 
            {
                // create red light thread and wait for it to join
                System.out.printf("Traffic light %d: ", index);
                RedLight r = new RedLight();
                r.start();
                try {
                    r.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // create green light thread and wait for it to join
                System.out.printf("Traffic light %d: ", index);
                GreenLight g = new GreenLight();
                g.start();
                try {
                    g.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // create yellow light thread and wait for it to join
                System.out.printf("Traffic light %d: ", index);
                YellowLight y = new YellowLight();
                y.start();
                try {
                    y.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Press Ctrl+C to exit.");
        System.out.printf("Starting the Traffic Signal program with %d traffic lights.\n", n);

        // creates n traffic lights
        for (int i = 1; i <= n; i++) 
        {
            Signal s = new Signal(i);
            s.start();
            // 500ms lag from previous instance of traffic light:
            try {
                Thread.sleep(lag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}