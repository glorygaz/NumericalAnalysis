package org.neu.operation;

import org.neu.dataStructure.Matrix;

public class GaussElimination { //高斯消去法
    public static Matrix gaussElimination(Matrix A, Matrix b) throws Exception {
        Matrix x = new Matrix(b.getRow(), b.getCol());
        Matrix bcopy = new Matrix(Matrix.copyMatrix(b));

        int row = A.getRow();
        if(A.getCol() != A.getRow()){
            throw new Exception("GaussEliminationFailed");
        }

        double[][] a = Matrix.copyMatrix(A); //复制矩阵

        for(int i = 0; i < row; i++){   //遍历每一列,从左到右
            for (int j = i+1; j < row; j++) {   //从本行往下一行开始遍历
                double lij = a[j][i]/a[i][i];
                for (int k = i; k < row; k++) { //遍历一行
                    a[j][k] -= lij*a[i][k];
                }
                bcopy.getMatrix()[j][0] -= lij*bcopy.getMatrix()[i][0];
            }
        }

        for (int i = row-1; i >= 0; i--) {   //求解
            double sum = 0;
            for (int j = i; j < row; j++) {
                sum += a[i][j]*x.getMatrix()[j][0];
            }
            x.getMatrix()[i][0] = (bcopy.getMatrix()[i][0] - sum)/a[i][i];
        }
        return x;
    }
}
