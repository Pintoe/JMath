package com.jmath.testing.linearalgebra;

import com.jmath.main.linearalgebra.Matrix;

public class SystemsOfEquations {
    public static void main(String[] args) {
        Matrix exampleMatrix = new Matrix(new double[][] {
                {1, 1, 1, 1, 1, -1},
                {1, 1, 1, 1, -1, 1},
                {1, 1, 1, -1, 1, 1},
                {1, 1, -1, 1, 1, 1},
                {1, -1, 1, 1, 1, 1},
                {-1, 1, 1, 1, 1, 1}
        });

        Matrix matrixOfConstants = new Matrix(new double[][] {
                {38},
                {324},
                {23},
                {85},
                {2},
                {42}
        });

        exampleMatrix.getInverseMatrix().getProductMatrix(matrixOfConstants).printValues();
    }
}
