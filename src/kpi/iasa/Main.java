package kpi.iasa;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static int NUMBER_OF_THREADS=2;
    public static void printMatrix(String method,String name,double [][]matrix,int size)
    {
        System.out.println(method+" "+name+":");
        for (int i = 0; i <size ; i++) {
            for (int j = 0; j <size ; j++) {
                System.out.print(String.format("%.2f",matrix[i][j])+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int n=3;
        double [][]A={
                {10,-7,0},
                {-3,6,2},
                {5,-1,5}};

        Random random=new Random();
        //for higher capacity of matrix
//        double[][]A=new double[n][n];
//
//        for (int i = 0; i <n ; i++) {
//            for (int j = 0; j <n ; j++) {
//                A[i][j]=random.nextInt(200);
//            }
//        }


        double[][] L =new double[n][n];
        double[][] L1=new double[n][n];

        double[][] U = Arrays.stream(A).map(double[]::clone).toArray(double[][]::new);
        double[][] U1 = Arrays.stream(A).map(double[]::clone).toArray(double[][]::new);


        long parallelTime= new LUParallelOb(L,U,n).makeLU();

        printMatrix("","A",A,n);

        printMatrix("Parallel","L",L,n);
        printMatrix("Parallel","U",U,n);

        System.out.println("Parallel time:"+parallelTime*Math.pow(10,-6));


        long consistentTime=0;
        LUConsistent luConsistent=new LUConsistent(A,L1,U1,n);
        long startTime=System.nanoTime();
        luConsistent.LU2();
        long endTime=System.nanoTime();
        consistentTime=endTime-startTime;


        printMatrix("Consistent","L",L1,n);
        printMatrix("Consistent","U",U1,n);



        System.out.println("Consistent time:"+consistentTime*Math.pow(10,-6));


    }
}
