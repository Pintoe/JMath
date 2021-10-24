package com.jmath.main.linearalgebra;

public class Vector2 extends Vector {

    public Vector2(double... coordinates) {
        super(coordinates);
    }

    public Vector2 getRotatedVector(double theta) {
        Vector2 rotatedVector = new Vector2(this.components);
        rotatedVector.rotate(theta);
        return rotatedVector;
    }

    public void rotate(double theta) {

    }
}
