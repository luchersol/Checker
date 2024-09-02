package specialized_checkers;

import static util.Message.sendMessage;

import java.util.Arrays;
import java.util.function.Predicate;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.util.Precision;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerMatrix<T extends Number> extends AbstractChecker<T[][]> {

    private static final String INIT_MATRIX = "matrix";

    public CheckerMatrix(T[][] object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }
    
    @Override
    public CheckerMatrix<T> is(Predicate<T[][]> predicate, String message) {
        super.is(predicate, message);
        return this;
    }

    @Override
    public CheckerMatrix<T> is(Predicate<T[][]> predicate) {
        super.is(predicate);
        return this;
    }

    @Override
    public CheckerMatrix<T> isNot(Predicate<T[][]> predicate, String message) {
        super.isNot(predicate, message);
        return this;
    }

    @Override
    public CheckerMatrix<T> isNot(Predicate<T[][]> predicate) {
        super.isNot(predicate);
        return this;
    }

    public CheckerMatrix<T> isEmpty() {
        is(matrix -> matrix.length == 0 && matrix[0].length == 0, sendMessage(INIT_MATRIX, "is_empty"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_square"));
        return this;
    }

    public CheckerMatrix<T> isZero() {
        is(matrix -> matrix.length > 0
                && Arrays.stream(matrix).flatMap(Arrays::stream).allMatch(elem -> elem.doubleValue() == 0),
                sendMessage(INIT_MATRIX, "is_zero"));
        return this;
    }

    public CheckerMatrix<T> isIdentity() {
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            int n = matrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j && matrix[i][j].doubleValue() != 1)
                        return false;
                    else if (matrix[i][j].doubleValue() != 0)
                        return false;
                }
            }
            return true;
        };
        is(predicate, sendMessage(INIT_MATRIX, "is_identity"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_symmetric"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_diagonal"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_orthogonal"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_invertible"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_positive_definite"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_upper_triangular"));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "is_lower_triangular"));
        return this;
    }

    public CheckerMatrix<T> hasRank(int rank){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            SingularValueDecomposition svd = new SingularValueDecomposition(mat);
            return Precision.equals(svd.getRank(), rank);
        };
        is(predicate, sendMessage(INIT_MATRIX, "has_rank", rank));
        return this;
    }

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
        is(predicate, sendMessage(INIT_MATRIX, "has_real_eigenvalues"));
        return this;
    }

    public CheckerMatrix<T> isFullRank(){
        isSquare();
        Predicate<T[][]> predicate = matrix -> {
            RealMatrix mat = new Array2DRowRealMatrix(convertToDoubleMatrix(matrix));
            SingularValueDecomposition svd = new SingularValueDecomposition(mat);
            int rank = svd.getRank();
            return rank == Math.min(matrix.length, matrix[0].length);
        };
        is(predicate, sendMessage(INIT_MATRIX, "is_full_rank"));
        return this;
    }

    public CheckerMatrix<T> anyMatch(Predicate<T> predicate) {
        is(matrix -> Arrays.stream(matrix).flatMap(Arrays::stream).anyMatch(elem -> predicate.test(elem)),
                sendMessage(INIT_MATRIX, "any_match"));
        return this;
    }

    public CheckerMatrix<T> allMatch(Predicate<T> predicate) {
        is(matrix -> Arrays.stream(matrix).flatMap(Arrays::stream).allMatch(elem -> predicate.test(elem)),
                sendMessage(INIT_MATRIX, "all_match"));
        return this;
    }

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
