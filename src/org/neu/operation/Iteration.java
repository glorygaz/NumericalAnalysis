package org.neu.operation;

import org.neu.dataStructure.Matrix;

public class Iteration {
    private int scale;  //小数点后的位数要求
    private int k = 0; //迭代次数
    private int kMax = 10000000; //最大迭代次数
    private IterationMethod iter; //迭代函数
    private Matrix xAcc;    //精确解

    public Iteration(int scale, IterationMethod iter, Matrix xAcc) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        this.scale = scale;
        this.iter = iter;
        this.xAcc = xAcc;
    }

    /**
     *Function:迭代
     */
    public Matrix iteration() throws Exception {
        Matrix xn = null;
        while(k < kMax){
            xn = iter.process();
            if(checkPoint(xn, xAcc)){
                return xn;
            }
            iter.setX(xn);
            k ++;
        }
        return xn;
    }

    /**
     * Function:检查精度
     */
    private boolean checkPoint(Matrix xn, Matrix xAcc){
        double max = 0;
        double temp;
        for (int i = 0; i < xAcc.getRow(); i++) {
            if(max < (temp = Math.abs(xn.getMatrix()[i][0] - xAcc.getMatrix()[i][0]))){
                max = temp;
            }
        }
        double scaleDouble = creatScaleNum(scale);
        return (max < scaleDouble);
    }

    /**
     * Function:创建精度小数
     */
    private double creatScaleNum(int scale){
        String s = "0.";
        for (int i = 0; i < scale-1; i++) {
            s += "0";
        }
        s += "1";
        return Double.valueOf(s);
    }

    public int getK() {
        return k;
    }
}
