package org.neu.operation;

import org.neu.dataStructure.Matrix;

public interface IterationMethod {
    Matrix process() throws Exception;
    void setX(Matrix x);
}
