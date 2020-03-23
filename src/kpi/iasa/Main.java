package kpi.iasa;

public class Main {
    public static int NUMBER_OF_THREADS=2;
    public static void printMatrix(String name,double [][]matrix,int size)
    {
        System.out.println(name+":");
        for (int i = 0; i <size ; i++) {
            for (int j = 0; j <size ; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int n=3;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        Thread []threadsArray=new Thread[NUMBER_OF_THREADS];
        double [][]A={
                {2,1,3},
                {11,7,5},
                {9,8,4}};
        printMatrix("A",A,n);
        int threadNumber=0;
        for(int i=0;i<NUMBER_OF_THREADS;i++) {
        threadsArray[i]=new Thread(new LUParallel(A,L,U,n,threadNumber++,NUMBER_OF_THREADS));
        threadsArray[i].start();
        threadsArray[i].join();
        }

        printMatrix("L",L,n);
        printMatrix("U",U,n);


    }
}
