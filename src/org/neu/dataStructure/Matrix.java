package org.neu.dataStructure;

import java.math.BigDecimal;

public class Matrix {
    private double [][] matrix;

    private int row;
    private int col;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        matrix = new double[row][col];
    }

    public Matrix(double[][] matrix) {
        this.setMatrix(matrix);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
        this.row = matrix.length;
        this.col = matrix[0].length;
    }

    /**
     * Function:Matrix Add
     * @param a:Matrix
     * @param b:Matrix
     * @return :Matrix
     */
    public static Matrix add(Matrix a, Matrix b) throws Exception {
        int row = a.getRow();
        int col = a.getCol();
        if (row != b.getRow() || col != b.getCol()) {
            throw new Exception("AddFailed,Can't Add");
        }
        Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result.getMatrix()[i][j] = a.getMatrix()[i][j] + b.getMatrix()[i][j];
            }
        }
        return result;
    }

    /**
     * Function:Matrix Sub
     * @param a:Matrix
     * @param b:Matrix
     * @return :Matrix
     */
    public static Matrix sub(Matrix a, Matrix b) throws Exception {
        int row = a.getRow();
        int col = a.getCol();
        if (row != b.getRow() || col != b.getCol()) {
            throw new Exception("SubtractFailed,Can't Subtract");
        }
        Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result.getMatrix()[i][j]= a.getMatrix()[i][j] - b.getMatrix()[i][j];
            }
        }
        return result;
    }

    /**
     * Function:Matrix Multiple Matrix
     * @param a:Matrix
     * @param b:Matrix
     * @return :Matrix
     */
    public static Matrix mul(Matrix a, Matrix b) throws Exception {
        int row = a.getRow();
        int col = b.getCol();
        if (a.getCol() != b.getRow()) {
            throw new Exception("MultipleFailed,Can't Multiple");
        }
        Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < a.getCol(); k++) {
                    result.getMatrix()[i][j] += a.getMatrix()[i][k]*b.getMatrix()[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Function:Matrix Multiple Number
     * @param a Matrix
     * @param b double
     * @return :Matrix
     */
    public static Matrix mul(Matrix a, double b) {
        int row = a.getRow();
        int col = a.getCol();
        Matrix result = new Matrix(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result.getMatrix()[i][j] = a.getMatrix()[i][j] * b;
            }
        }
        return result;
    }

    /**
     * Function:Square Matrix Inversion
     * @param a :Matrix
     * @return :Matrix
     */
    public static Matrix inver(Matrix a) throws Exception {
        int row = a.getRow();
        if(a.getCol() != a.getRow()){       //方阵才能使用初等行变换求逆
            throw new Exception("InversionFailed,Not Square");
        }

        double[][] copy = copyMatrix(a);

        Matrix result = new Matrix(row, row);
        double[][] resultM = result.getMatrix();
        for(int i = 0; i < row; i++){   //将result矩阵置为单位矩阵
            resultM[i][i] = 1;
        }
        for(int i = 0; i < row; i++){   //遍历每一列,从左到右
            double aii = copy[i][i];
            for(int j = 0; j < row; j++){   //遍历行中每个元素，除以aii
                resultM[i][j] /= aii;
                copy[i][j] /= aii;
            }
            for(int j = i+1; j < row; j++){   //遍历本行以下的每一行
                elementaryRowTrans(row, copy, resultM, i, j);
            }
        }
        for(int i = row-1; i >= 0 ; i--){   //遍历每一列,从右到左
            for(int j = i-1; j >= 0; j--){   //遍历本行以上的每一行
                elementaryRowTrans(row, copy, resultM, i, j);
            }
        }
        return result;
    }

    /**
     * Function:CopyMatrix
     * @param a:Matrix
     * @return :double[][]
     */
    public static double[][] copyMatrix(Matrix a) {
        double[][] copy = new double[a.getMatrix().length][];    //复制矩阵
        for (int i = 0; i < copy.length; i++) {
            copy[i] = a.getMatrix()[i].clone();
        }
        return copy;
    }

    /**
     *Function:初等行变换
     */
    private static void elementaryRowTrans(int row, double[][] copy, double[][] resultM, int i, int j) {
        double coef = -copy[j][i];  //每行系数
        for (int k = 0; k < row; k++) {   //遍历行中每个元素
            resultM[j][k] += coef * resultM[i][k];
            copy[j][k] += coef * copy[i][k];
        }
    }

    /**
     * Function:Print Matrix
     * @param matrix :Matrix
     */
    public static void print(Matrix matrix) {
        double [][] mat = matrix.getMatrix();
        for (int i = 0; i < mat.length; i++) {
            System.out.print("[");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j]);
                if (j != mat[0].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("]\n");
        }
    }

    /**
     * Function:生成全一矩阵
     */
    public static Matrix ones(int row, int col){
        Matrix a = new Matrix(row, col);
        double[][] matrix = a.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 1;
            }
        }
        return a;
    }

    /**
     * Function:生成全0矩阵
     */
    public static Matrix zeros(int row, int col){
        Matrix a = new Matrix(row, col);
        double[][] matrix = a.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
        return a;
    }

    /**
     * Function:生成无对角线的上三角矩阵
     */
    public static Matrix upperTriangularMatrix(Matrix a){
        Matrix result = new Matrix(a.getRow(), a.getCol());
        for (int i = 0; i < a.getRow(); i++) {
            for (int j = i+1; j < a.getCol(); j++) {
                result.getMatrix()[i][j] = a.getMatrix()[i][j];
            }
        }
        return result;
    }

    /**
     * Function:生成无对角线的下三角矩阵
     */
    public static Matrix lowerTriangularMatrix(Matrix a){
        Matrix result = new Matrix(a.getRow(), a.getCol());
        for (int i = 0; i < a.getCol(); i++) {
            for (int j = i+1; j < a.getRow(); j++) {
                result.getMatrix()[j][i] = a.getMatrix()[j][i];
            }
        }
        return result;
    }
    /**
     * Function:生成对角矩阵
     */
    public static Matrix diagonalMatrix(Matrix a) throws Exception {
        if(a.getCol() != a.getRow()){
            throw new Exception("DiagonalMatrixFailed,Not Square");
        }
        Matrix result = new Matrix(a.getRow(), a.getCol());
        for (int i = 0; i < a.getRow(); i++) {
            result.getMatrix()[i][i] = a.getMatrix()[i][i];
        }
        return result;
    }
}
