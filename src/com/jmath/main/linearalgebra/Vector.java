package com.jmath.main.linearalgebra;

import java.lang.Math;

public class Vector {

    public final int dimension;
    public final double[] components;

    public Vector(double ...components) {
        this.components = components;
        this.dimension = components.length;
    }

    public void set(int index, double value) {
        this.components[index] = value;
    }

    public double get(int index) {
        return this.components[index];
    }

    public void normalize() {
        double magnitude = this.calculateMagnitude();

        for (int i = 0; i < this.dimension; i++) {
            this.set(i, this.get(i)/magnitude);
        }
    }

    public Vector getUnitVector() {
        Vector normalizedVector = new Vector(this.components);
        normalizedVector.normalize();
        return normalizedVector;
    }

    public double calculateMagnitude() {
        double magnitude = 0;

        for (int i = 0; i < this.dimension; i++) {
            magnitude += Math.pow(this.get(i), 2);
        }

        return Math.sqrt(magnitude);
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

    public Vector unit() {
        return this.getUnitVector();
    }

    public double getAngleBetween(Vector other) {
        double dotProduct = this.unit().dot(other.unit());
        System.out.println(dotProduct);
        if (dotProduct > 0.9999D) {
            return 0;
        } else if (dotProduct < -0.9999D) {
            return Math.PI;
        } else if (dotProduct > -0.0001D && dotProduct < 0.0001D) {
            return Math.PI/2;
        }

        return Math.acos(dotProduct);
    }

    public void printCoordinates() {
        StringBuilder lineString = new StringBuilder("[");

        for (int i = 0; i < this.dimension; i++) {
            lineString.append(this.components[i]).append((i == this.dimension - 1) ? "]" : ", ");
        }

        System.out.println(lineString);
    }
}
