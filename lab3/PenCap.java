package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PenCap {

    static class Pen {
        private String color;

        //constructor
        public Pen(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    static class Cap {
        private String color;

        //constructor
        public Cap(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }
    }

    static class MatchingMachine {
        public synchronized boolean matchPensAndCaps(Pen pen, Cap cap) {
            // Matching logic
            return pen.getColor().equals(cap.getColor());
        }
    }

    static class RoboticArm extends Thread {
        private List<Pen> pens;
        private List<Cap> caps;
        private MatchingMachine matchingMachine;

        public RoboticArm(List<Pen> pens, List<Cap> caps, MatchingMachine matchingMachine) {
            this.pens = pens;
            this.caps = caps;
            this.matchingMachine = matchingMachine;
        }

        @Override
        public void run() {
            while (true) {
                Pen pen;
                Cap cap;

                synchronized (pens) {
                    if (pens.isEmpty()) {
                        continue; // Wait if no pens available
                    }
                    pen = pens.remove(0);
                }

                synchronized (caps) {
                    if (caps.isEmpty()) {
                        pens.add(pen); // Put back the pen if no caps available
                        continue; // Wait if no caps available
                    }
                    cap = caps.remove(0);
                }

                // Simulate robotic arm picking up a pen and a cap
                boolean isMatch = matchingMachine.matchPensAndCaps(pen, cap);

                if (isMatch) {
                    System.out.println("Matched: " + pen.getColor() + " pen and " + cap.getColor() + " cap");
                    // Simulate placing the matched pair on the shelf
                } else {
                    // If not a match, put the pen and cap back
                    pens.add(pen);
                    caps.add(cap);
                }
            }
        }
    }

    
    public static void main(String[] args) 
    {
        List<Pen> pens = new ArrayList<>();
        List<Cap> caps = new ArrayList<>();
        MatchingMachine matchingMachine = new MatchingMachine();

        // Populate pens and caps
        for (String color : new String[]{"red", "blue", "green", "black"}) {
            pens.add(new Pen(color));
            caps.add(new Cap(color));
        }

        // Create robotic arms
        for (int i = 0; i < 2; i++) {
            new RoboticArm(pens, caps, matchingMachine).start();
        }

        // Simulate pens and caps being added to the system
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            synchronized (pens) {
                pens.add(new Pen("red"));
            }

            synchronized (caps) {
                caps.add(new Cap("red"));
            }

            try {
                Thread.sleep(random.nextInt(1000)); // Simulate time between adding pens and caps
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
