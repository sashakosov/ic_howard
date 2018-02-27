package ic_howard;
import java.util.*;

public class ICDynSystem {
    private final ICSysMatrix matrixP;
    private final ICSysMatrix matrixR;
    private final double[][] vectorQ;
    private int size;

    public ICDynSystem(double[][][] mP, double[][][] mR) {
        matrixP = new ICSysMatrix(mP);
        matrixR = new ICSysMatrix(mR);
        size = mP.length;
        vectorQ = new double[matrixP.getSizeXY()][];

        for (int i = 0; i < matrixP.getSizeXY(); i++) {
            vectorQ[i] = new double[matrixP.getSizeK(i)];
            for (int k = 0; k < matrixP.getSizeK(i); k++) {
                for (int j = 0; j < matrixP.getSizeXY(); j++) {
                    vectorQ[i][k] += (matrixP.getValue(i, j, k)
                            * matrixR.getValue(i, j, k));
                }
            }
        }
    }

    private double[][] getGaussMatrix(int[] decision) {
        double[][] temp
                = new double[matrixP.getSizeXY() + 1][matrixP.getSizeXY() + 2];

        for (int i = 0; i < matrixP.getSizeXY(); i++) {
            temp[i][matrixP.getSizeXY() + 1] = vectorQ[i][decision[i]];
            for (int j = 0; j < matrixP.getSizeXY(); j++) {
                if (i == j) {
                    temp[i][j + 1] = 1 - matrixP.getValue(i, j, decision[i]);
                }
                else {
                    temp[i][j + 1] = -(matrixP.getValue(i, j, decision[i]));
                }
            }
        }

        for (int i = 0; i < matrixP.getSizeXY() + 1; i++) {
            for (int j = 0; j < matrixP.getSizeXY() + 1; j++) {
                if ((j == 0 && i != matrixP.getSizeXY())
                        || (i == matrixP.getSizeXY()
                        && j == matrixP.getSizeXY())) {
                    temp[i][j] = 1;
                }
            }
        }
        return temp;
    }

    public double[] determineWeights(int[] decision) {
        return ICGauss.solve(getGaussMatrix(decision));
    }

    public int[] improveDecision(double[] weights) {
        double[][] temp = new double[matrixP.getSizeXY()][matrixP.getSizeXY()];

        for (int i = 0; i < matrixP.getSizeXY(); i++) {
            for (int k = 0; k < matrixP.getSizeK(i); k++) {
                temp[i][k] = vectorQ[i][k];
                for (int j = 0; j < matrixP.getSizeXY(); j++) {
                    temp[i][k] += (matrixP.getValue(i, j, k) * weights[j+1]);
                }
            }
        }

        int[] newDecision = new int[matrixP.getSizeXY()];

        for (int i = 0; i < matrixP.getSizeXY(); i++) {
            double max = temp[i][0];
            int maxStr = 0;
            for (int k = 1; k < matrixP.getSizeK(i); k++) {
                if (max < temp[i][k]) {
                    max = temp[i][k];
                    maxStr = k;
                }
            }
            newDecision[i] = maxStr;
        }

        return newDecision;
    }

    public int[] getOptimalDecision() {
        System.out.println("НАХОЖДЕНИЕ ОПТИМАЛЬНОГО РЕШЕНИЯ "
                + "ИТЕРАЦИОННЫМ МЕТОДОМ");

        int[] oldDecision = new int[matrixP.getSizeXY()];
        int[] newDecision = new int[matrixP.getSizeXY()];
        double[] weights = new double[matrixP.getSizeXY() + 1];
        int count = 1;

        System.out.print("Начальное решение: d = [");
        for (int i : oldDecision){
            System.out.print((i + 1));
        }
        System.out.println("]");
        System.out.println();

        do {
            System.out.println("ИТЕРАЦИЯ " + count);

            oldDecision = Arrays.copyOf(newDecision, newDecision.length);
            weights = determineWeights(oldDecision);

            System.out.println("Прибыль системы: g = " + weights[0]);
            System.out.print("Относительные веса: ");
            for (int i = 1; i < (weights.length); i++){
                System.out.print("v" + i + " = " + weights[i] + " ");
            }
            System.out.println();

            newDecision = improveDecision(weights);

            System.out.print("Улучшенное решение: d = [");
            for (int i : newDecision){
                System.out.print((i + 1));
            }
            System.out.println("]");
            System.out.println();
            count++;
        }
        while (!Arrays.equals(oldDecision, newDecision));

        System.out.print("Оптимальное решение: d = [");
        for (int i : newDecision){
            System.out.print((i + 1));
        }
        System.out.println("]");

        return newDecision;
    }

    public void printSystem() {
        for (int i = 0; i < matrixP.getSizeXY(); i++) {
            System.out.print((i+1));
            for (int k = 0; k < matrixP.getSizeK(i); k++) {
                System.out.print("\t" + (k+1));
                for (int j = 0; j < matrixP.getSizeXY(); j++) {
                    System.out.print("\t" + matrixP.getValue(i, j, k));
                }
                for (int j = 0; j < matrixP.getSizeXY(); j++) {
                    System.out.print("\t" + matrixR.getValue(i, j, k));
                }
                System.out.print("\t" + vectorQ[i][k]);
                System.out.println();
            }
        }
    }


}
