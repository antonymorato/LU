package kpi.iasa;

import java.lang.reflect.Array;

public class LUConsistent {

    private double [][]A;
    private  double [][]L;
    private double [][]U;
    private int n;
    public LUConsistent( double [][]a,double[][] l, double[][] u, int n) {
        A=a;
        L = l;
        U = u;
        this.n = n;
    }


    public void LU2(){
        for (int i = 0; i < n; i++) {
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
