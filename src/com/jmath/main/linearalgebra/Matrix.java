package com.jmath.main.linearalgebra;

/**
 * Class to create Matrix objects
 */
public class Matrix {

    /**
     * Container for Matrix values
     */
    public double[][] components;

    /**
     * Integers to keep track of the Matrix size
     * Matrix represented by sizeN (Rows) x sizeM (Columns)
     */
    public int sizeN;
    public int sizeM;

    /**
     * Constructor function
     *
     * @param matrixValues      2d array that represents the matrix values
     */
    public Matrix(double[][] matrixValues) {
        this.sizeN = matrixValues.length;
        this.sizeM = matrixValues[0].length;
        this.components = matrixValues;
    }

    /**
     * Constructor function
     *
     * @param sizeN     Integer which represents the amount of rows in the Matrix
     * @param sizeM     Integer which represents the amount of columns in the Matrix
     */
    public Matrix(int sizeN, int sizeM) {
        this.sizeN = sizeN;
        this.sizeM = sizeM;
        this.components = new double[sizeN][sizeM];
        this.clearValues();
    }

    /**
     * Auxiliary function that adds/subtracts two Matrices
     *
     * @param other     Second Matrix (Size: this.sizeN x this.sizeM)
     * @param command   Operation that should be done to object that called the method and the Matrix 'other'
     * @return          Sum/Difference of the two matrices
     */
    private Matrix simpleOperation(Matrix other, Operation command) {
        // TODO: Add Error Checking
        Matrix endMatrix = new Matrix(this.sizeN, this.sizeM);
        boolean addValues = command == Operation.ADD;

        for (int n = 0; n < this.sizeN; n++) {
            for (int m  = 0; m < this.sizeM; m++) {
                double c1 = this.get(n, m);
                double c2 = other.get(n, m);
                endMatrix.set(n, m, addValues ? c1 + c2: c1 - c2);
            }
        }

        return endMatrix;
    }

    /**
     * Increment an index by value specified
     *
     * @param nIndex    Row Index
     * @param mIndex    Column Index
     * @param value     Value to increment by
     */
    public void increment(int nIndex, int mIndex, double value) {
        this.components[nIndex][mIndex] += value;
    }

    /**
     * Sets the components variable of current object Matrix to specified array
     * along with setting the size variables to the appropriate value
     *
     * @param newMatrix     2d array of what components will be set to
     */
    public void set(double[][] newMatrix) {
        this.sizeN = newMatrix.length;
        this.sizeM = newMatrix[0].length;
        this.components = newMatrix;
    }

    /**
     * Sets the specified index to the value specified
     *
     * @param nIndex    Row Index
     * @param mIndex    Column Index
     * @param value     Value the specified index will be assigned to
     */
    public void set(int nIndex, int mIndex, double value) {
        this.components[nIndex][mIndex] = value;
    }

    /**
     * Gets the value associated to the specified index
     *
     * @param nIndex    Row Index
     * @param mIndex    Column Index
     * @return          Indexed value
     */
    public double get(int nIndex, int mIndex) {
        return this.components[nIndex][mIndex];
    }

    /**
     * Negates the Matrix of the current object
     *
     * @return      deep copied Negated Matrix object
     */
    public Matrix getNegateMatrix() {
        Matrix negatedMatrix = new Matrix(this.components);

        for (int n = 0; n < negatedMatrix.sizeN; n++) {
            for (int m = 0; m < negatedMatrix.sizeM; m++) {
                negatedMatrix.set(n, m, -negatedMatrix.get(n, m));
            }
        }

        return negatedMatrix;
    }

    /**
     * Gets the sum of two Matrices
     *
     * @param other     Second Matrix that should be added to the current Matrix object (Size: this.SizeN x this.SizeM)
     * @return          deep copied Matrix that holds the sum of the two Matrices
     */
    public Matrix getSumMatrix(Matrix other) {
        return this.simpleOperation(other, Operation.ADD);
    }

    /**
     * Gets the difference of two Matrices
     *
     * @param other     Second Matrix that the current object will be subtracted from (Size: this.SizeN x this.SizeM)
     * @return          deep copied Matrix that holds the difference of the two Matrices
     */
    public Matrix getDifferenceMatrix(Matrix other) {
        return this.simpleOperation(other, Operation.SUBTRACT);
    }

    /**
     * Gets the product of two Matrices
     *
     * @param other     Second Matrix that will be multiplied with the current object (Size: this.SizeM x any)
     * @return          deep copied Matrix that holds that product of the two Matrices
     */
    public Matrix getProductMatrix(Matrix other) {

        // TODO: Add Error Checking
        Matrix finalMatrix = new Matrix(this.sizeN, other.sizeM);

        for (int n = 0; n < this.sizeN; n++) { // row

            for (int m = 0; m < other.sizeM; m++) { // column

                for (int k = 0; k < this.sizeM; k++) { // varying index

                    double c1 = this.get(n, k); // component 1
                    double c2 = other.get(k, m); // component 2
                    finalMatrix.increment(n, m, c1 * c2); // calculating

                }
            }
        }

        return finalMatrix;
    }

    /**
     * Multiplies the current object with a scalar value
     *
     * @param other     Double value that will be multiplied with each of the current object values
     * @return          deep copied Matrix that holds that product of the Matrix and scalar
     */
    public Matrix getProductMatrix(double other) {
        Matrix productMatrix = new Matrix(this.sizeN, this.sizeM);

        for (int n = 0; n < this.sizeN; n++) {
            for (int m = 0; m < this.sizeM; m++) {
                productMatrix.set(n, m, this.get(n, m) * other);
            }
        }

        return productMatrix;
    }

    /**
     * Gets the transposed Matrix
     *
     * @return      Transposed Matrix of the current object
     */
    public Matrix getTransposedMatrix() {
        double[][] transposedValues = new double[this.sizeM][this.sizeN];

        for (int m = 0; m < this.sizeM; m++) {
            for (int n = 0; n < this.sizeN; n++) {
                transposedValues[m][n] = this.get(n, m);
            }
        }

        return new Matrix(transposedValues);
    }

    /**
     * Gets a Matrix that ignores the rows and columns specified
     *
     * @param rowToIgnore       Index of row to ignore
     * @param columnToIgnore    Index of column to ignore
     * @return                  n-1 by m-1 Matrix object with the specified row and column ignored
     */
    public Matrix getModifiedMatrix(int rowToIgnore, int columnToIgnore) {
        Matrix modifiedMatrix = new Matrix(this.sizeN-1, this.sizeM-1);

        int nCounter = -1;
        for (int n = 0; n < this.sizeN; n++) {
            if (n == rowToIgnore) continue;
            nCounter += 1;

            int mCounter = -1;
            for (int m = 0; m < this.sizeM; m++) {
                if (m == columnToIgnore) continue;
                mCounter += 1;

                modifiedMatrix.set(nCounter, mCounter, this.get(n, m));
            }
        }

        return modifiedMatrix;
    }

    /**
     * Gets the matrix of cofactors
     *
     * @return      Calculated matrix of cofactors of the current object's components
     */
    public Matrix getMatrixOfCofactors() {
        Matrix matrixOfCofactors = new Matrix(this.components);

        for (int n = 0; n < this.sizeN; n++) {

            for (int m = 0; m < this.sizeM; m++) {
                this.set(n, m, Math.pow(-1, n+m)*this.get(n, m));
            }
        }

        return matrixOfCofactors;
    }

    /**
     * Gets the inverse matrix
     *
     * @return Inverse matrix of the current object's components
     */
    public Matrix getInverseMatrix() {
        double det;

        // TODO: Throw Exception
        if (!this.isSquare()) {
            return null;
        } else {
            det = this.calculateDeterminant();
            // TODO: Throw Exception
            if (det == 0) {
                return null;
            }
        }

        if (this.sizeN == 2) {
            return new Matrix(
                    new double[][] {
                        {this.get(1, 1), -this.get(0, 1)},
                        {-this.get(1, 0), this.get(0, 0)}
                    }
            ).getProductMatrix(1/this.calculateDeterminant());
        }

        Matrix minorMatrix = new Matrix(this.sizeN, this.sizeM);
        double inverseDeterminant = 1/det;

        for (int n = 0; n < this.sizeN; n++) {
            for (int m = 0; m < this.sizeM; m++) {
                minorMatrix.set(n, m, calculateDeterminant(this.getModifiedMatrix(n, m))*Math.pow(-1, n+m)*inverseDeterminant);
            }
        }

        return minorMatrix;
    }

    /**
     * Changes the components of the current object and negates it
     */
    public void negateMatrix() {
        this.set(this.getNegateMatrix().components);
    }

    /**
     * Sets all the values in the current object Matrix to the integer '0'
     */
    public void clearValues() {
        for (int n = 0; n < this.sizeN; n++) {
            for (int m = 0; m < this.sizeM; m++) {
                this.set(n, m, 0);
            }
        }
    }

    /**
     * Adds two Matrices together and sets the current object's components to the sum
     *
     * @param other     Matrix that will be added to the current object's Matrix (Size: this.sizeN x this.sizeM)
     */
    public void add(Matrix other) {
        this.set(this.getSumMatrix(other).components);
    }

    /**
     * Subtracts two Matrices and sets the current object's components to the difference
     *
     * @param other     Matrix that will be subtracted from the current object's Matrix (Size: this.sizeN x this.sizeM)
     */
    public void subtract(Matrix other) {
        this.set(this.getDifferenceMatrix(other).components);
    }

    /**
     * Multiplies two Matrices together and sets the current object's components to the product
     *
     * @param other     Matrix that will be multiplied with the current object's Matrix (Size: this.sizeM x any)
     */
    public void multiply(Matrix other) {
        this.set(this.getProductMatrix(other).components);
    }

    /**
     * Multiplies the current object's Matrix with a scalar and sets the current object's components to the product
     *
     * @param other     Scalar that will be multiplied with the current object's Matrix
     */
    public void multiply(double other) {
        this.set(this.getProductMatrix(other).components);
    }

    /**
     * Gets the transposes the current object's Matrix and sets the current object's components to that value
     */
    public void transpose() {
        this.set(this.getTransposedMatrix().components);
    }

    /**
     * Calculates the trace value of the current object's components
     *
     * @return      Trace value
     */
    public double calculateTrace() {
        // TODO: Throw Exception
        if (!this.isSquare()) return 0;

        double sum = 0;

        for (int i = 0; i < this.sizeN; i++) {
            sum += this.get(i, i);
        }

        return sum;
    }

    /**
     * Recursively calculates the determinant of the current object's components
     *
     * @param currentMatrix     Matrix to calculate the determinant of
     * @return                  Determinant of the current object
     */
    private double calculateDeterminant(Matrix currentMatrix) {
        if (currentMatrix.sizeN != 2) {

            double determinant = 0;
            for (int m = 0; m < currentMatrix.sizeM; m++) {
                double currentDeterminant = currentMatrix.calculateDeterminant(currentMatrix.getModifiedMatrix(0, m))*currentMatrix.get(0, m);
                if (m % 2 == 1) {
                    determinant -= currentDeterminant;
                } else {
                    determinant += currentDeterminant;
                }
            }

            // n.get(0, 0) - n.get(0, 1) + n.get(0, 2) ...

            return determinant;
        } else {
            return currentMatrix.get(0, 0)*currentMatrix.get(1, 1)
                    -currentMatrix.get(0, 1)*currentMatrix.get(1, 0);
        }

    }

    /**
     * Calculates the Determinant of the component
     *
     * @return      Determinant of the component array
     */
    public double calculateDeterminant() {
        // TODO: Throw Exception
        if (!this.isSquare()) return 0;

        return calculateDeterminant(this);
    }

    /**
     * Checks if the current object's component represents a square Matrix
     *
     * @return      Boolean of whether the component is square or not
     */
    public boolean isSquare() {
        return this.sizeN == this.sizeM;
    }

    /**
     * Checks if two Matrices are equal to each other
     *
     * @param other     Matrix that will be compared to the current object
     * @return          Boolean of whether the two Matrices are equal
     */
    public boolean equals(Matrix other) {

        if (!(this.sizeN == other.sizeN) || !(this.sizeM == other.sizeM)) {
            return false;
        }

        for (int n = 0; n < this.sizeN; n++) {
            for (int m = 0; m < this.sizeM; m++) {
                if (this.get(n, m) == other.get(n, m)) {
                    continue;
                }

                return false;
            }
        }

        return true;
    }

    /**
     * Auxiliary function which prints the dimensions of the current object's components
     * {this.sizeN}x{this.sizeM}
     */
    public void printDimensions() {
        System.out.println(this.sizeN + "x" + this.sizeM);
    }

    /**
     * Prints all the values of the current object's component
     */
    public void printValues() {
        for (int n = 0; n < this.sizeN; n++) {
            StringBuilder lineString = new StringBuilder("[");
            for (int m = 0; m < this.sizeM; m++) {
                lineString.append(this.components[n][m]).append((m == this.sizeM - 1) ? "]" : ", ");
            }
            System.out.println(lineString);
        }
        System.out.println("");
    }
}
