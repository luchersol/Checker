package specialized_checkers;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;


class CheckerColorTest {

    @Test
    void testIsBlack() {
        assertDoesNotThrow(() -> new CheckerColor(Color.BLACK, "black").isBlack());
        assertThrows(Exception.class, () -> new CheckerColor(Color.WHITE, "white").isBlack());
    }

    @Test
    void testIsWhite() {
        assertDoesNotThrow(() -> new CheckerColor(Color.WHITE, "white").isWhite());
        assertThrows(Exception.class, () -> new CheckerColor(Color.BLACK, "black").isWhite());
    }

    @Test
    void testIsGray() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(128, 128, 128), "gray").isGray());
        assertThrows(Exception.class, () -> new CheckerColor(new Color(128, 127, 128), "not_gray").isGray());
    }

    @Test
    void testIsColor() {
        Color c = new Color(10, 20, 30);
        assertDoesNotThrow(() -> new CheckerColor(c, "custom").isColor(new Color(10, 20, 30)));
        assertThrows(Exception.class, () -> new CheckerColor(c, "custom").isColor(Color.BLACK));
    }

    @Test
    void testIsDarkDefault() {
        assertDoesNotThrow(() -> new CheckerColor(Color.BLACK, "black").isDark());
        assertThrows(Exception.class, () -> new CheckerColor(Color.WHITE, "white").isDark());
    }

    @Test
    void testIsDarkWithThreshold() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(50, 50, 50), "dark").isDark(100));
        assertThrows(Exception.class, () -> new CheckerColor(new Color(200, 200, 200), "light").isDark(100));
    }

    @Test
    void testIsLightDefault() {
        assertDoesNotThrow(() -> new CheckerColor(Color.WHITE, "white").isLight());
        assertThrows(Exception.class, () -> new CheckerColor(Color.BLACK, "black").isLight());
    }

    @Test
    void testIsLightWithThreshold() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(200, 200, 200), "light").isLight(150));
        assertThrows(Exception.class, () -> new CheckerColor(new Color(50, 50, 50), "dark").isLight(50));
    }

    @Test
    void testIsTransparent() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(0, 0, 0, 100), "transparent").isTransparent());
        assertThrows(Exception.class, () -> new CheckerColor(new Color(0, 0, 0, 255), "opaque").isTransparent());
    }

    @Test
    void testHasContrastDefault() {
        assertDoesNotThrow(() -> new CheckerColor(Color.BLACK, "black").hasContrast(Color.WHITE));
        assertThrows(Exception.class, () -> new CheckerColor(Color.BLACK, "black").hasContrast(new Color(10, 10, 10)));
    }

    @Test
    void testHasContrastWithThreshold() {
        assertDoesNotThrow(() -> new CheckerColor(Color.BLACK, "black").hasContrast(Color.WHITE, 100));
        assertThrows(Exception.class, () -> new CheckerColor(new Color(100, 100, 100), "gray").hasContrast(new Color(110, 110, 110), 20));
    }

    @Test
    void testHasAlpha() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(10, 10, 10, 123), "alpha").hasAlpha(123));
        assertThrows(Exception.class, () -> new CheckerColor(new Color(10, 10, 10, 200), "alpha").hasAlpha(123));
    }

    @Test
    void testIsDesaturated() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(128, 128, 128), "gray").isDesaturated(0.1));
        assertThrows(Exception.class, () -> new CheckerColor(Color.RED, "red").isDesaturated(0.1));
    }

    @Test
    void testIsSimilar() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(10, 10, 10), "c1").isSimilar(new Color(12, 12, 12), 5));
        assertThrows(Exception.class, () -> new CheckerColor(new Color(10, 10, 10), "c1").isSimilar(new Color(100, 100, 100), 5));
    }

    @Test
    void testIsOpaque() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(10, 10, 10, 255), "opaque").isOpaque());
        assertThrows(Exception.class, () -> new CheckerColor(new Color(10, 10, 10, 100), "transparent").isOpaque());
    }

    @Test
    void testHasHexadecimal() {
        assertDoesNotThrow(() -> new CheckerColor(new Color(255, 0, 0), "red").hasHexadecimal("#FF0000"));
        assertThrows(Exception.class, () -> new CheckerColor(new Color(0, 255, 0), "green").hasHexadecimal("#FF0000"));
    }
}
