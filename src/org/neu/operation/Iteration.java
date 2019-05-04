package org.neu.operation;

import org.neu.dataStructure.Matrix;

import java.math.BigDecimal;

public class Iteration {
    private int scale;  //小数点后的位数要求
    private int k = 0; //迭代次数
    private int kMax = 100000; //最大迭代次数
    private IterationMethod iter; //迭代函数

    public Iteration(int scale, IterationMethod iter) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        this.scale = scale;
        this.iter = iter;
    }

    /**
     *Function:迭代
     */
    public Matrix iteration() throws Exception {
        Matrix xn = null;
        while(k < kMax){
            xn = iter.process();
//            if(checkPoint(xn, iter.getX())){
//                return xn;
//            }
            iter.setX(xn);
            k ++;
        }
        return xn;
    }

    /**
     * Function:检查精度
     */
//    private boolean checkPoint(Matrix x, Matrix xn){
//        double max = 0;
//        double temp;
//        for (int i = 0; i < x.getRow(); i++) {
//            if(max < (temp = Math.abs(xn.getMatrix()[i][0] - x.getMatrix()[i][0]))){
//                max = temp;
//            }
//        }
//        return new BigDecimal(max).setScale(scale,BigDecimal.ROUND_DOWN).doubleValue() == 0;
//    }

    public void setkMax(int kMax) {
        this.kMax = kMax;
    }

    public int getK() {
        return k;
    }
}
