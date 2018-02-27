package ic_howard;
import java.util.*;

public class ICTest {

    public static void main(String[] args) {
        double[][][] exP3 =
                {
                        {
                                {0.5, 0.0625, 0.25},
                                {0.25, 0.75, 0.125},
                                {0.25, 0.1875, 0.625}
                        },
                        {
                                {0.5, 0.0625},
                                {0.0, 0.875},
                                {0.5, 0.0625}
                        },
                        {
                                {0.25, 0.125, 0.75},
                                {0.25, 0.75, 0.0625},
                                {0.5, 0.125, 0.1875}
                        }
                };

        double[][][] exR3 =
                {
                        {
                                {10, 8, 4},
                                {4, 2, 6},
                                {8, 4, 4}
                        },
                        {
                                {14, 8},
                                {0.0, 16},
                                {18, 8}
                        },
                        {
                                {10, 6, 4},
                                {2, 4, 0},
                                {8, 2, 8}
                        }
                };

        ICDynSystem test3 = new ICDynSystem(exP3, exR3);
        test3.printSystem();
        System.out.println();
        test3.getOptimalDecision();
        System.out.println();


        double[][][] exP1 =
                {
                        {
                                {0.5, 0.8},
                                {0.5, 0.2}
                        },
                        {
                                {0.4, 0.7},
                                {0.6, 0.3}
                        }
                };

        double[][][] exR1 =
                {
                        {
                                {9, 4},
                                {3, 4}
                        },
                        {
                                {3, 1},
                                {-7, -19}
                        }
                };

        ICDynSystem test1 = new ICDynSystem(exP1, exR1);
        test1.printSystem();
        test1.getOptimalDecision();

    }

}

//    public DynSystem(int N, int[] strat) {
//        matrixP = new SysMatrix(N, strat);
//        matrixR = new SysMatrix(N, strat);
//        size = N;
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                for (int k = 0; k < strat[i]; k++) {
//                    System.out.println("Введите вероятность перехода из "
//                            + "состояния " + (i+1) + " в состояние " + (j+1)
//                            + " в стратегии " + (k+1) + ": ");
//                    matrixP.addValue(i, j, k, in.nextDouble());
//                }
//                System.out.println();
//            }
//        }
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                for (int k = 0; k < strat[i]; k++) {
//                    System.out.println("Введите вероятность перехода из "
//                            + "состояния " + (i+1) + " в состояние " + (j+1)
//                            + " в стратегии " + (k+1) + ": ");
//                    matrixR.addValue(i, j, k, in.nextDouble());
//                }
//                System.out.println();
//            }
//        }
//
//        matrixP.printMatrix();
//        matrixR.printMatrix();
