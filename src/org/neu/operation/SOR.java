package org.neu.operation;

import org.neu.dataStructure.Matrix;

public class SOR implements IterationMethod{
    private Matrix M;
    private Matrix g;
    private Matrix x;
    /**
     * Function: SOR
     * @param A 系数矩阵
     * @param x 初始解
     * @param b 常数矩阵
     * @param w 参数
     */
    public SOR(Matrix A, Matrix x, Matrix b, double w) throws Exception {
        Matrix D = Matrix.diagonalMatrix(A);
        Matrix L = Matrix.mul(Matrix.lowerTriangularMatrix(A),-1);
        Matrix U = Matrix.mul(Matrix.upperTriangularMatrix(A), -1);
        //M = (D-wL)^-1*[(1-w)D+wU]
        M = Matrix.mul(Matrix.inver(Matrix.sub(D, Matrix.mul(L, w))),
                Matrix.add(Matrix.mul(D, 1-w), Matrix.mul(U,w)));
        //g = w(D-wL)^-1*b
        g = Matrix.mul(Matrix.mul(Matrix.inver(Matrix.sub(D, Matrix.mul(L, w))), b), w);
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

    @Override
    public Matrix getX() {
        return x;
    }
}
