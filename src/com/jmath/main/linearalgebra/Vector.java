package com.jmath.main.linearalgebra;

public class Vector {

    public int dimension;

    private double[] components;

    public Vector(double ...components) {
        this.components = components;
        this.dimension = components.length;
    }

    public double get(int index) {
        return this.components[index];
    }

    public double calculateDotProduct(Vector other) {
        // TODO: Throw Exception
        if (!(this.dimension == other.dimension)) return 0;

        double sum = 0;

        for (int i = 0; i < this.dimension; i++) {
            sum += this.get(i)*other.get(i);
        }

        return sum;
    }

    public double dot(Vector other) {
        return this.calculateDotProduct(other);
    }

    public void printCoordinates() {
        StringBuilder lineString = new StringBuilder("[");

        for (int i = 0; i < this.dimension; i++) {
            lineString.append(this.components[i]).append((i == this.dimension - 1) ? "]" : ", ");
        }

        System.out.println(lineString);
    }
}
