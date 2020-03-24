package kpi.iasa;


import java.util.Arrays;

import static kpi.iasa.Main.NUMBER_OF_THREADS;

public class LUParallelOb {
    private Object lock;
    private long time;
    Thread []threadsArray;
    private int threadNumber;
    private double[][] L;
    private double[][] U;
    private int n;
    private double[][] A;
    public LUParallelOb(double[][] l, double[][] u, int n) {
        L = l;
        U = u;
        this.n = n;
        A = Arrays.stream(U).map(double[]::clone).toArray(double[][]::new);
    }

    {   threadsArray=new Thread[NUMBER_OF_THREADS];
        lock=new Object();
        time=0L;
        threadNumber=0;
    }
    public Long makeLU() throws InterruptedException {
        for(int i=0;i<NUMBER_OF_THREADS;i++) {
            threadsArray[i]=new Thread(new LUParallel(L,U,n,threadNumber++));
            threadsArray[i].start();
        }
        for (Thread t:threadsArray)
            t.join();


        return time;
    }

    class LUParallel implements Runnable {

        private double[][] L;
        private double[][] U;
        private int n;
        private int threadNumber;



        public LUParallel(double[][] L, double[][] U, int size, int threadNumber) {
            this.L = L;
            this.U = U;
            n = size;
            this.threadNumber = threadNumber;


        }

        @Override
        public void run() {
            Long startTime = System.nanoTime();
            LU2();
            Long endTime = System.nanoTime();
                time += (endTime - startTime);
        }



    public void LU2()
    {
        for (int i = threadNumber; i < n; i+=NUMBER_OF_THREADS) {
            for (int j = 0; j < n; j++) {
                U[0][i] =A[0][i];
                L[i][0] =A[i][0] /U[0] [0];
                double sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += L[i][ k] *U[k][j];
                }
                U[i][j] =A[i][j]-sum;
                if (i > j) {
                    L[j][i] =0;
                } else {
                    sum = 0;
                    for (int k = 0; k < i; k++) {
                        sum += L[j][k] *U[k][i];
                    }
                    L[j][i] =(A[j][i]-sum) /U[i][i];
                }

            }
        }
    }
    }
}