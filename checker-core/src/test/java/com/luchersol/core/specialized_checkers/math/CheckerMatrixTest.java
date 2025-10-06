package com.luchersol.core.specialized_checkers.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.luchersol.core.util.CheckerException;

class CheckerMatrixTest {

    @Test
    void testIsEmpty() {
        Integer[][] empty = new Integer[0][0];
        assertDoesNotThrow(() -> new CheckerMatrix<>(empty, "empty").isEmpty());
    }

    @Test
    void testIsSquare() {
        Integer[][] square = { {1, 2}, {3, 4} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(square, "square").isSquare());

        Integer[][] nonSquare = { {1, 2, 3}, {4, 5, 6} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(nonSquare, "nonSquare").isSquare());
    }

    @Test
    void testIsZero() {
        Integer[][] zero = { {0, 0}, {0, 0} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(zero, "zero").isZero());

        Integer[][] notZero = { {0, 1}, {0, 0} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notZero, "notZero").isZero());
    }

    @Test
    void testIsIdentity() {
        Integer[][] identity = { {1, 0}, {0, 1} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(identity, "identity").isIdentity());

        Integer[][] notIdentity = { {1, 1}, {0, 1} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notIdentity, "notIdentity").isIdentity());
    }

    @Test
    void testIsSymmetric() {
        Integer[][] symmetric = { {1, 2}, {2, 1} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(symmetric, "symmetric").isSymmetric());

        Integer[][] notSymmetric = { {1, 2}, {3, 1} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notSymmetric, "notSymmetric").isSymmetric());
    }

    @Test
    void testIsDiagonal() {
        Integer[][] diagonal = { {1, 0}, {0, 2} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(diagonal, "diagonal").isDiagonal());

        Integer[][] notDiagonal = { {1, 2}, {0, 2} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notDiagonal, "notDiagonal").isDiagonal());
    }

    @Test
    void testIsUpperTriangular() {
        Integer[][] upper = { {1, 2}, {0, 3} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(upper, "upper").isUpperTriangular());

        Integer[][] notUpper = { {1, 0}, {2, 3} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notUpper, "notUpper").isUpperTriangular());
    }

    @Test
    void testIsLowerTriangular() {
        Integer[][] lower = { {1, 0}, {2, 3} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(lower, "lower").isLowerTriangular());

        Integer[][] notLower = { {1, 2}, {0, 3} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notLower, "notLower").isLowerTriangular());
    }

    @Test
    void testIsInvertible() {
        Double[][] invertible = { {1.0, 2.0}, {3.0, 4.0} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(invertible, "invertible").isInvertible());

        Double[][] singular = { {1.0, 2.0}, {2.0, 4.0} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(singular, "singular").isInvertible());
    }

    @Test
    void testIsPositiveDefinite() {
        Double[][] positiveDefinite = { {2.0, -1.0}, {-1.0, 2.0} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(positiveDefinite, "positiveDefinite").isPositiveDefinite());

        Double[][] notPositiveDefinite = { {0.0, 1.0}, {1.0, 0.0} };
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(notPositiveDefinite, "notPositiveDefinite").isPositiveDefinite());
    }

    @Test
    void testHasRank() {
        Double[][] rank2 = { {1.0, 2.0}, {3.0, 4.0} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(rank2, "rank2").hasRank(2));

        Double[][] rank1 = { {1.0, 2.0}, {2.0, 4.0} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(rank1, "rank1").hasRank(1));
    }

    @Test
    void testAnyMatch() {
        Integer[][] matrix = { {1, 2}, {3, 4} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(matrix, "matrix").anyMatch(x -> x == 3));
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(matrix, "matrix").anyMatch(x -> x == 5));
    }

    @Test
    void testAllMatch() {
        Integer[][] matrix = { {2, 2}, {2, 2} };
        assertDoesNotThrow(() -> new CheckerMatrix<>(matrix, "matrix").allMatch(x -> x == 2));
        assertThrows(CheckerException.class, () -> new CheckerMatrix<>(matrix, "matrix").allMatch(x -> x == 1));
    }

    @Test
    void testConvertToDoubleMatrix() {
        Integer[][] ints = { {1, 2}, {3, 4} };
        double[][] doubles = CheckerMatrix.convertToDoubleMatrix(ints);
        assertEquals(1.0, doubles[0][0]);
        assertEquals(4.0, doubles[1][1]);
    }
}
