package com.jmath.testing;

import com.jmath.main.linearalgebra.Vector;

public class GeneralVectorTesting {
    public static void main(String[] args) {
        Vector v = new Vector(new double[]{1, 1, 1});
        v.printCoordinates();
        System.out.println(v.dot(v));
    }
}
