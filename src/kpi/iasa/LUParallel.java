package kpi.iasa;

public class LUParallel implements Runnable {

    private double [][]A;
    private double [][]L;
    private double [][]U;
    private int n;
    private int threadNumber;
    private int numberOfThreads;

    public LUParallel(double [][]A,double [][]L,double [][]U,int size,int threadNumber,int numberOfThreads)
    {
        this.A=A;
        this.L=L;
        this.U=U;
        n=size;
        this.threadNumber=threadNumber;
        this.numberOfThreads=numberOfThreads;
    }
    @Override
    public void run() {
        LU();
    }

    public void LU()
    {
        for (int i = threadNumber; i < n; i+=numberOfThreads) {
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
