package org.neu.main;

import org.neu.dataStructure.Hilbert;
import org.neu.dataStructure.Matrix;
import org.neu.operation.GaussElimination;

import java.io.FileOutputStream;
import java.io.OutputStream;


public class GSETest {      //生成高斯消去法的误差数据
    public static void main(String[] args) {
        try {
            OutputStream os = new FileOutputStream("GSEError.txt");
            for (int i = 1; i <= 30; i++) {
                double error = doOneTime(i);
                byte[] buffer = String.valueOf(error).getBytes();
                os.write(buffer);
                if(i != 30){
                    buffer = " ".getBytes();
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static double doOneTime(int dimension) throws Exception {
        Hilbert hilbert = new Hilbert(dimension);
        Matrix xAcc = Matrix.ones(dimension, 1);
        Matrix b = Matrix.mul(hilbert, xAcc);
        Matrix gse = GaussElimination.gaussElimination(hilbert, b);
        double max = 0;
        double temp;
        for (int i = 0; i < xAcc.getRow(); i++) {
            if(max < (temp=Math.abs(gse.getMatrix()[i][0] - xAcc.getMatrix()[i][0]))){
                max = temp;
            }
        }
        return max;
    }
}
