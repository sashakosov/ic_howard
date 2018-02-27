package ic_howard;
import java.util.*;

public class ICSysMatrix {
    private final double[][][] sysMatrix;
    private final int[] sizeK;

    public ICSysMatrix(double[][][] arr) {
        sysMatrix = Arrays.copyOf(arr, arr.length);
        sizeK = new int[sysMatrix.length];
        for (int i = 0; i < sysMatrix.length; i++) {
            sizeK[i] = sysMatrix[i][0].length;
        }
    }

    public int getSizeXY() {
        return sysMatrix.length;
    }

    public int getSizeK(int i) {
        return sizeK[i];
    }

    public double getValue(int i, int j, int k) {
        return sysMatrix[i][j][k];
    }

//    public SysMatrix(int N){
//        size = N;
//        matr = new double[N][N][];
//        strategy = new int[N];
//
//        for (int i = 0; i < N; i++) {
//            System.out.println("Введите количество стратегий в состоянии "
//                    + (i+1) + ": ");
//            strategy[i] = in.nextInt();
//            for(int j = 0; j < N; j++) {
//                matr[i][j] = new double[strategy[i]];
//            }
//        }
//
//        System.out.println();
//    }
//
//    public SysMatrix(int N, int[] strat) {
//        size = N;
//        matr = new double[N][N][];
//        strategy = strat;
//
//        for (int i = 0; i < N; i++) {
//            for(int j = 0; j < N; j++) {
//                matr[i][j] = new double[strategy[i]];
//            }
//        }
//    }

}
