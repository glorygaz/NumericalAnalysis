package org.neu.operation;

import org.neu.dataStructure.Matrix;

public interface IterationMethod {
    Matrix process() throws Exception;
    Matrix getX();
    void setX(Matrix x);
}
