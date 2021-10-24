package com.jmath.testing.linearalgebra;

import com.jmath.main.linearalgebra.Vector2;

public class GeneralVectorTesting {
    public static void main(String[] args) {
        Vector2 v = new Vector2(0, 1);
        v.printCoordinates();
        System.out.println(v.getAngleBetween(new Vector2(1, 0)));
        v.unit().printCoordinates();
    }
}
