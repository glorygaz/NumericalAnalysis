package org.neu.main;

import org.neu.dataStructure.Hilbert;
import org.neu.dataStructure.Matrix;
import org.neu.operation.*;


public class Test {
    public static void main(String[] args) {
        try {
            doOneTime(6,10, 1.3);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doOneTime(int dimension, int scale, double w) throws Exception {
        Hilbert hilbert = new Hilbert(dimension);
//        double[][] a_a = {{10,-1,-2}, {-1,10,-2}, {-1,-1,5}};
//        double[][] b_b = {{7.2},{8.3},{4.2}};
//        Matrix A = new Matrix(a_a);
//        Matrix B = new Matrix(b_b);
        Matrix xAcc = Matrix.ones(dimension,1);
        Matrix xi = Matrix.zeros(dimension,1);
        Matrix b = Matrix.mul(hilbert, xAcc);
        Matrix.print(Matrix.inver(hilbert));
        System.out.println(dimension+"维高斯消去法结果：");
        Matrix.print(GaussElimination.gaussElimination(hilbert, b));
        System.out.println("\n");

        System.out.println(dimension+"维，精度"+scale+"，Jacobi迭代法结果：");
        Iteration iter = new Iteration(scale, new Jacobi(hilbert, xi, b));
        Matrix.print(iter.iteration());
        System.out.println("迭代次数："+iter.getK());
        System.out.println("\n");

        System.out.println(dimension+"维，精度："+scale+"，GaussSeidel迭代法结果：");
        iter = new Iteration(scale, new GaussSeidel(hilbert, xi, b));
        Matrix.print(iter.iteration());
        System.out.println("迭代次数："+iter.getK());
        System.out.println("\n");

        System.out.println(dimension+"维，精度："+scale+"，参数w："+w+"，SOR迭代法结果：");
        iter = new Iteration(scale, new SOR(hilbert, xi, b, w));
        Matrix.print(iter.iteration());
        System.out.println("迭代次数："+iter.getK());
        System.out.println("\n");
    }
}
