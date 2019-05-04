package org.neu.operation;

import org.neu.dataStructure.Matrix;

public class Jacobi implements IterationMethod{
    private Matrix M;
    private Matrix g;
    private Matrix x;

    /**
     * Function: Jacobi迭代法
     * @param A 系数矩阵
     * @param x 初始解
     * @param b 常数矩阵
     */
    public Jacobi(Matrix A, Matrix x, Matrix b) throws Exception {
        Matrix D = Matrix.diagonalMatrix(A);
        Matrix L = Matrix.mul(Matrix.lowerTriangularMatrix(A),-1);
        Matrix U = Matrix.mul(Matrix.upperTriangularMatrix(A), -1);
        //M = D^-1(L+U)
        M = Matrix.mul(Matrix.inver(D), Matrix.add(L, U));
        //g = D^-1*b
        g = Matrix.mul(Matrix.inver(D), b);
        this.x = x;
    }

    public Matrix process() throws Exception {
        //xn = Mx + g
        Matrix xn = Matrix.add(Matrix.mul(M, x), g);
        return xn;
    }

    @Override
    public void setX(Matrix x) {
        this.x = x;
    }
}
