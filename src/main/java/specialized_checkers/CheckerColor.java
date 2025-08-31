package specialized_checkers;

import static util.Message.*;

import java.awt.Color;
import java.util.function.Predicate;

import util.AbstractChecker;

/**
 * CheckerColor is a specialized checker for validating and performing assertions on {@link Color} values.
 * <p>
 * It provides a fluent API for common color validations such as checking color equality, brightness, transparency, contrast, and more.
 */
public class CheckerColor extends AbstractChecker<Color, CheckerColor> {

    private static final String INIT_COLOR = "color";
    private static final String DEFAULT_NAME = "Color";

    /**
     * Constructs a new {@code CheckerColor} with the specified color and name.
     *
     * @param color the {@link Color} to be associated with this checker
     * @param name the name of the checker
     */
    protected CheckerColor(Color color, String name) {
        super(color, name);
    }

    /**
     * Creates a new CheckerColor instance for the given color and name.
     *
     * @param color the color value to be checked
     * @param name  the name to identify the color in error messages
     * @return a CheckerColor instance for further validations
     */
    public static CheckerColor check(Color color, String name) {
        return new CheckerColor(color, name);
    }

    /**
     * Creates a new CheckerColor instance for the given color with a default name.
     *
     * @param color the color value to be checked
     * @return a CheckerColor instance for further validations
     */
    public static CheckerColor check(Color color) {
        return check(color, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerColor instance
     */
    @Override
    protected CheckerColor self() {
        return this;
    }

    /**
     * Checks if the color is black.
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isBlack(){
        return is(color -> color.equals(Color.BLACK), sendMessage(INIT_COLOR, "is_black"));
    }

    /**
     * Checks if the color is white.
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isWhite(){
        return is(color -> color.equals(Color.WHITE), sendMessage(INIT_COLOR, "is_white"));
    }

    /**
     * Checks if the color is a shade of gray (red, green, and blue components are equal).
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isGray(){
        return is(color -> color.getRed() == color.getGreen() && color.getGreen() == color.getBlue(), sendMessage(INIT_COLOR, "is_gray"));
    }

    /**
     * Checks if the color is equal to another color.
     *
     * @param other the color to compare with
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isColor(Color other){
        return is(color -> color.equals(other), sendMessage(INIT_COLOR, "is_color", other));
    }

    /**
     * Checks if the color is considered dark using the default threshold (128).
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isDark(){
        return isDark(128);
    }

    /**
     * Checks if the color is considered dark using the specified threshold.
     *
     * @param umbral the threshold for darkness (lower value means darker)
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isDark(double umbral){
        Predicate<Color> predicate = color -> {
            double luminosidad = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
            return luminosidad < umbral;
        };

        return is(predicate, sendMessage(INIT_COLOR, "is_dark", umbral));
    }

    /**
     * Checks if the color is considered light using the default threshold (128).
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isLight(){
        return isLight(128);
    }

    /**
     * Checks if the color is considered light using the specified threshold.
     *
     * @param umbral the threshold for lightness (higher value means lighter)
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isLight(double umbral){
        Predicate<Color> predicate = color -> {
            double luminosidad = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
            return luminosidad > umbral;
        };

        return is(predicate, sendMessage(INIT_COLOR, "is_light", umbral));
    }

    /**
     * Checks if the color is transparent (alpha value less than 255).
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isTransparent(){
        return is(color -> color.getAlpha() < 255, sendMessage(INIT_COLOR, "is_transparent"));
    }

    /**
     * Checks if the color has sufficient contrast with another color using the default threshold (128).
     *
     * @param other the color to compare contrast with
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor hasContrast(Color other){
        return hasContrast(other, 128);
    }

    /**
     * Checks if the color has sufficient contrast with another color using the specified threshold.
     *
     * @param other  the color to compare contrast with
     * @param umbral the threshold for contrast difference
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor hasContrast(Color other, double umbral){
        Predicate<Color> predicate = color -> {
            double luminancia1 = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
            double luminancia2 = 0.2126 * other.getRed() + 0.7152 * other.getGreen() + 0.0722 * other.getBlue();
            return Math.abs(luminancia1 - luminancia2) > umbral;
        };
        return is(predicate, sendMessage(INIT_COLOR, "has_contrast", umbral));
    }

    /**
     * Checks if the color has the specified alpha (transparency) value.
     *
     * @param alpha the expected alpha value
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor hasAlpha(int alpha){
        return is(color -> color.getAlpha() == alpha, sendMessage(INIT_COLOR, "has_alpha", alpha));
    }

    /**
     * Checks if the color is desaturated (saturation below the specified threshold).
     *
     * @param umbral the threshold for saturation (0 = grayscale, 1 = fully saturated)
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isDesaturated(double umbral) {
        Predicate<Color> predicate = color ->{
            float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            return hsb[1] < umbral;
        };
        return is(predicate, sendMessage(INIT_COLOR, "is_desaturated"));
    }

    /**
     * Checks if the color is similar to another color within the specified threshold.
     *
     * @param other  the color to compare with
     * @param umbral the maximum allowed distance for similarity
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isSimilar(Color other, double umbral) {
        Predicate<Color> predicate = color -> {
            int r1 = color.getRed(), g1 = color.getGreen(), b1 = color.getBlue();
            int r2 = other.getRed(), g2 = other.getGreen(), b2 = other.getBlue();
            double distancia = Math.sqrt(Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2, 2));
            return distancia < umbral;
        };
        return is(predicate, sendMessage(INIT_COLOR, "is_similar"));
    }

    /**
     * Checks if the color is fully opaque (alpha value is 255).
     *
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor isOpaque(){
        return is(color -> color.getAlpha() == 255, sendMessage(INIT_COLOR, "is_opaque"));
    }

    /**
     * Checks if the color's hexadecimal representation matches the specified string.
     *
     * @param hex the expected hexadecimal string (e.g., "#FFFFFF")
     * @return this CheckerColor instance for chaining
     */
    public CheckerColor hasHexadecimal(String hex){
        Predicate<Color> predicate = color -> {
            int rgb = color.getRGB();
            String hexadecimal = String.format("#%06X", (0xFFFFFF & rgb));
            return hexadecimal.equals(hex);
        };
        return is(predicate, sendMessage(INIT_COLOR, "has_hexadecimal"));
    }

}
