package org.neu.main;

import org.neu.dataStructure.Hilbert;
import org.neu.dataStructure.Matrix;
import org.neu.operation.Iteration;
import org.neu.operation.SOR;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class SORTest {      //生成SOR迭代法的迭代次数数据
    public static void main(String[] args) {
        try {
            OutputStream os = new FileOutputStream("SORTimes.txt");
            for (int i = 1; i <= 100; i++) {
                int times = doOneTime(i);
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
    private static int doOneTime(int dimension) throws Exception {
        Hilbert hilbert = new Hilbert(dimension);
        Matrix xAcc = Matrix.ones(dimension,1);
        Matrix xi = Matrix.zeros(dimension,1);
        Matrix b = Matrix.mul(hilbert, xAcc);
        Iteration iter = new Iteration(2, new SOR(hilbert, xi, b, 1.46), xAcc);
        iter.iteration();
        return iter.getK();
    }
}
