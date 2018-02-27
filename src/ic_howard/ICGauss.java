package ic_howard;

public class ICGauss {
    public static double[] solve(double[][] arr) {
        int size = arr.length;
        double[][] A = arr.clone();
        double[] X = new double[size];

        // Прямой ход метода Гаусса
        for (int k = 0; k < size; k++) {
            for (int j = size; j >= k; j--) {
                A[k][j] /=  A[k][k];
            }
            for (int i = k + 1; i < size; i++) {
                for (int j = size; j >= k; j--) {
                    A[i][j] -= A[i][k] * A[k][j];
                }
            }
        }

        // Прямой ход метода Гаусса
        for(int i = size - 1; i >= 0; i--) {
            double temp = 0;
            for(int j = i + 1; j < size; j++) {
                temp += A[i][j] * X[j];
            }
            X[i] = A[i][size] - temp;
        }

        return X;

//       for(int k = 0;k<=n-1;k++) {
//           Amax = A[k][k];
//            int l = k;
//            for(int i = k+1;i<n;i++) {
//                //Максимум по модулю
//                if(Math.abs(A[i][k])>Math.abs(Amax)) {
//                     Amax = A[i][k] ;
//                    l = i;
//                               }
//
//                                     }
//            if(k!=l) {
//                //Замена уравнений
//                for(int j = 0;j<n;j++) {
//                    double temp = 0;
//                    temp = A[l][j];
//                    A[l][j] = A[k][j];
//                    A[k][j] = temp;
//                                       }
//
//                    }
    }

}
