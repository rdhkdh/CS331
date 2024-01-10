// Ridhiman Kaur Dhindsa, 210101088

package lab1;
import java.util.Random;

public class MatrixMultiplication {
    //global variables
    static final int N = 4;
    static final int MAX_THREAD = N;
    static int[][] A = new int[N][N];
    static int[][] B = new int[N][N];
    static int[][] C = new int[N][N];
    static int step_i = 0;

    static class Worker implements Runnable{
        int i;

        //constructor
        Worker(int i)
        { this.i =i; }

        @Override
        public void run()
        {
            for(int j=0;j<N;j++)
            {
                for(int k=0;k<N;k++)
                {
                    C[i][j] = A[i][k] * B[k][j];
                }
            }
        }
    }

    public static void main(String args[])
    {
        Random rand = new Random();
 
        // Generating random values in matA and matB
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = rand.nextInt(50);
                if(A[i][j]==0) {A[i][j] = rand.nextInt(100);}
                B[i][j] = rand.nextInt(50);
                if(B[i][j]==0) {B[i][j] = rand.nextInt(100);}
            }
        }

        //display A,B
        System.out.println("\nMatrix A");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
 
        System.out.println("\nMatrix B");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.println();
        }

        //declare 4 threads
        Thread[] threads = new Thread[MAX_THREAD];

        for(int i=0;i<MAX_THREAD;i++)
        {
            threads[i] = new Thread(new Worker(step_i++));
            threads[i].start();
        }

        //join all
        for(int i=0;i<MAX_THREAD;i++)
        {
            try{
                threads[i].join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //display result
        System.out.println("\nMatrix multiplicatrion result:");
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                System.out.printf("%d ", C[i][j]);
            }
            System.out.println();
        }
    }
}
