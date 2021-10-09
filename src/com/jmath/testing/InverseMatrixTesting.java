package com.jmath.testing;

import com.jmath.main.Matrix;

public class InverseMatrixTesting {
    public static void main(String[] args) {
        Matrix exampleMatrix = new Matrix(new double[][] {
                {1, 1, 1, 1, -1},
                {1, 1, 1, -1, 1},
                {1, 1, -1, 1, 1},
                {1, -1, 1, 1, 1},
                {-1, 1, 1, 1, 1}
        });

        Matrix inverse = exampleMatrix.getInverseMatrix();
        System.out.println("Inverse Matrix Values: ");
        inverse.printValues();

        System.out.println("Proof: ");
        inverse.getProductMatrix(exampleMatrix).printValues();
    }
}
