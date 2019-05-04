package org.neu.dataStructure;

public class Hilbert extends Matrix{
    public Hilbert(int row) {
        super(row, row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                this.getMatrix()[i][j] = (double)1/(i+j+1);
            }
        }
    }
}
