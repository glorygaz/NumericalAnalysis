package org.neu.operation;

import org.neu.dataStructure.Matrix;

public class GaussSeidel extends SOR{
    /**
     * Function: GaussSeidel迭代法
     * @param A 系数矩阵
     * @param x 初始解
     * @param b 常数矩阵
     */
    public GaussSeidel(Matrix A, Matrix x, Matrix b) throws Exception {
        super(A, x, b, 1);
    }

    public Matrix process() throws Exception {
        return super.process();
    }
}
