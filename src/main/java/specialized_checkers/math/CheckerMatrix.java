package specialized_checkers.math;

import static util.Message.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.util.Precision;

import util.AbstractChecker;

/**
 * Checker for matrix (2D array) instances, providing fluent validation methods for matrices of any numeric type.
 * <p>
 * This class allows you to validate and assert properties of matrix objects in a fluent and readable way.
 * </p>
 *
 * @param <T> the type of the elements in the matrix being checked (must extend {@link Number})
 */
public class CheckerMatrix<T extends Number> extends AbstractChecker<T[][], CheckerMatrix<T>> {

    private static final String INIT_MATRIX = "math.matrix";
    private static final String DEFAULT_NAME = "Matrix";

    protected CheckerMatrix(T[][] matrix, String name) {
        super(matrix, name);
    }


    /**
     * Creates a new {@code CheckerMatrix} for the given matrix instance with a custom name.
     *
     * @param matrix the matrix instance to be checked
     * @param name   the name to identify this checker instance (useful for error messages)
     * @param <T>    the type of the elements in the matrix (must extend {@link Number})
     * @return a new {@code CheckerMatrix} for the provided matrix
     */
    public static <T extends Number> CheckerMatrix<T> check(T[][] matrix, String name){
        return new CheckerMatrix<>(matrix, name);
    }

    /**
     * Creates a new {@code CheckerMatrix} for the given matrix instance with a default name.
     *
     * @param matrix the matrix instance to be checked
     * @param <T>    the type of the elements in the matrix (must extend {@link Number})
     * @return a new {@code CheckerMatrix} for the provided matrix
     */
    public static <T extends Number> CheckerMatrix<T> check(T[][] matrix){
        return check(matrix, DEFAULT_NAME);
    }

    /**
     * Returns this checker instance (for fluent API usage).
     *
     * @return this {@code CheckerMatrix} instance
     */
    @Override
    protected CheckerMatrix<T> self() {
        return this;
    }

    /**
     * Asserts that the matrix is empty (has zero rows or all rows are empty).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isEmpty() {
        return is(matrix -> {
            if (matrix == null || matrix.length == 0) return true;
            return Arrays.stream(matrix).allMatch(row -> row == null || row.length == 0);
        }, sendMessage(INIT_MATRIX, "is_empty"));
    }

    /**
     * Asserts that the matrix is square (number of rows equals number of columns).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isSquare() {
        Predicate<T[][]> predicate = matrix -> {
            if (matrix.length == 0)
                return false;
            for (T[] ts : matrix) {
                if (ts.length != matrix.length) {
                    return false;
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_square"));
    }

    /**
     * Asserts that the matrix is a zero matrix (all elements are zero).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isZero() {
        is(matrix -> matrix.length > 0
                && Arrays.stream(matrix).flatMap(Arrays::stream).allMatch(elem -> elem.doubleValue() == 0),
                sendMessage(INIT_MATRIX, "is_zero"));
        return this;
    }

    /**
     * Asserts that the matrix is an identity matrix (ones on the diagonal, zeros elsewhere).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isIdentity() {
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j && matrix[i][j].doubleValue() != 1)
                        return false;
                    else if (i != j && matrix[i][j].doubleValue() != 0)
                        return false;
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_identity"));
    }

    /**
     * Asserts that the matrix is symmetric (equal to its transpose).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isSymmetric(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != matrix[j][i]) {
                        return false;
                    }
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_symmetric"));
    }

    /**
     * Asserts that the matrix is diagonal (all off-diagonal elements are zero).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isDiagonal(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j && matrix[i][j].doubleValue() != 0) {
                        return false;
                    }
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_diagonal"));
    }

    /**
     * Asserts that the matrix is orthogonal (its transpose is its inverse).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isOrthogonal(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            RealMatrix transpose = mat.transpose();
            RealMatrix product = mat.multiply(transpose);
            RealMatrix identity = new Array2DRowRealMatrix(matrix.length, matrix.length);
            for (int i = 0; i < matrix.length; i++) {
                identity.setEntry(i, i, 1);
            }
            return product.equals(identity);
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_orthogonal"));
    }

    /**
     * Asserts that the matrix is invertible (has a non-zero determinant).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isInvertible(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            try {
                LUDecomposition luDecomposition = new LUDecomposition(mat);
                return luDecomposition.getSolver().isNonSingular();
            } catch (Exception e) {
                return false;
            }
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_invertible"));
    }

    /**
     * Asserts that the matrix is positive definite (all eigenvalues are positive).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isPositiveDefinite(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            EigenDecomposition eigenDecomposition = new EigenDecomposition(mat);
            for (double eigenvalue : eigenDecomposition.getRealEigenvalues()) {
                if (eigenvalue <= 0)
                    return false;
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_positive_definite"));
    }

    /**
     * Asserts that the matrix is upper triangular (all elements below the main diagonal are zero).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isUpperTriangular(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            int n = matrix.length;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (matrix[i][j].doubleValue() != 0) {
                        return false;
                    }
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_upper_triangular"));
    }

    /**
     * Asserts that the matrix is lower triangular (all elements above the main diagonal are zero).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isLowerTriangular(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (matrix[i][j].doubleValue() != 0) {
                        return false;
                    }
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_lower_triangular"));
    }

    /**
     * Asserts that the matrix has the specified rank.
     *
     * @param rank the expected rank of the matrix
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> hasRank(int rank){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            SingularValueDecomposition svd = new SingularValueDecomposition(mat);
            return Precision.equals(svd.getRank(), rank);
        };
        return is(predicate, sendMessage(INIT_MATRIX, "has_rank", rank));
    }

    /**
     * Asserts that the matrix has only real eigenvalues.
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> hasRealEigenvalues(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            EigenDecomposition eigenDecomposition = new EigenDecomposition(mat);
            for (double eigenvalue : eigenDecomposition.getRealEigenvalues()) {
                if (Double.isNaN(eigenvalue)) {
                    return false;
                }
            }
            return true;
        };
        return is(predicate, sendMessage(INIT_MATRIX, "has_real_eigenvalues"));
    }

    /**
     * Asserts that the matrix is full rank (rank equals the minimum of the number of rows and columns).
     *
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> isFullRank(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            SingularValueDecomposition svd = new SingularValueDecomposition(mat);
            int rank = svd.getRank();
            return rank == Math.min(matrix.length, matrix[0].length);
        };
        return is(predicate, sendMessage(INIT_MATRIX, "is_full_rank"));
    }

    /**
     * Asserts that any element in the matrix matches the provided predicate.
     *
     * @param predicate the predicate to test elements
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> anyMatch(Predicate<T> predicate) {
        is(matrix -> Arrays.stream(matrix).flatMap(Arrays::stream).anyMatch(elem -> predicate.test(elem)),
                sendMessage(INIT_MATRIX, "any_match"));
        return this;
    }

    /**
     * Asserts that all elements in the matrix match the provided predicate.
     *
     * @param predicate the predicate to test elements
     * @return this {@code CheckerMatrix} instance for further validation
     */
    public CheckerMatrix<T> allMatch(Predicate<T> predicate) {
        is(matrix -> Arrays.stream(matrix).flatMap(Arrays::stream).allMatch(elem -> predicate.test(elem)),
                sendMessage(INIT_MATRIX, "all_match"));
        return this;
    }

    /**
     * Converts a matrix of any numeric type to a matrix of doubles.
     *
     * @param matrix the matrix to convert
     * @param <T>    the type of the elements in the matrix (must extend {@link Number})
     * @return a new matrix of doubles with the same dimensions as the input
     */
    public static <T extends Number> double[][] convertToDoubleMatrix(T[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] res = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = matrix[i][j].doubleValue();
            }
        }

        return res;
    }

}
