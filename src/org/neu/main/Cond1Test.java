package org.neu.main;

import org.neu.dataStructure.Hilbert;
import org.neu.dataStructure.Matrix;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Cond1Test {       //获取不同维度下的Hilbert矩阵的条件数和预处理后的条件数
    public static void main(String[] args) {
        try {
            OutputStream os = new FileOutputStream("Cond1PreTreatment.txt");
//            OutputStream os = new FileOutputStream("Cond1.txt");
            for (int i = 1; i <= 100; i++) {
                double times = doOneTime(i);
                byte[] buffer = String.valueOf(times).getBytes();
                os.write(buffer);
                if(i != 100){
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
//        return Matrix.cond1(hilbert);
        return Matrix.cond1(Matrix.preTreatment(hilbert));
    }
}
