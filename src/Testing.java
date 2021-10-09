import com.jmath.linearalgebra.Matrix;

public class Testing {
    public static void main(String[] args) {
        Matrix matrixOfCoefficients = new Matrix(new double[][] {
                {1, 1, 1, 1, -1},
                {1, 1, 1, -1, 1},
                {1, 1, -1, 1, 1},
                {1, -1, 1, 1, 1},
                {-1, 1, 1, 1, 1}
        });

        Matrix matrixOfSolutions = new Matrix(new double[][] {
                {90},
                {23},
                {42},
                {53},
                {28}
        });

        matrixOfCoefficients.getInverseMatrix().getProductMatrix(matrixOfSolutions).printValues();
    }
}
