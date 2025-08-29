package specialized_checkers;

import static util.Message.*;

import java.awt.Color;
import java.util.function.Predicate;

import util.AbstractChecker;

public class CheckerColor extends AbstractChecker<Color, CheckerColor> {

    private static final String INIT_COLOR = "color";
    private static final String DEFAULT_NAME = "Color";

    protected CheckerColor(Color color, String name) {
        super(color, name);
    }

    /**
     * @param color
     * @param name
     * @return CheckerColor
     */
    public static CheckerColor check(Color color, String name) {
        return new CheckerColor(color, name);
    }

    /**
     * @param color
     * @return CheckerColor
     */
    public static CheckerColor check(Color color) {
        return check(color, DEFAULT_NAME);
    }

    /**
     * @return CheckerColor
     */
    @Override
    protected CheckerColor self() {
        return this;
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isBlack(){
        return is(color -> color.equals(Color.BLACK), sendMessage(INIT_COLOR, "is_black"));
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isWhite(){
        return is(color -> color.equals(Color.WHITE), sendMessage(INIT_COLOR, "is_white"));
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isGray(){
        return is(color -> color.getRed() == color.getGreen() && color.getGreen() == color.getBlue(), sendMessage(INIT_COLOR, "is_gray"));
    }

    /**
     * @param other
     * @return CheckerColor
     */
    public CheckerColor isColor(Color other){
        return is(color -> color.equals(other), sendMessage(INIT_COLOR, "is_color", other));
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isDark(){
        return isDark(128);
    }

    /**
     * @param umbral
     * @return CheckerColor
     */
    public CheckerColor isDark(double umbral){
        Predicate<Color> predicate = color -> {
            double luminosidad = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
            return luminosidad < umbral;
        };

        return is(predicate, sendMessage(INIT_COLOR, "is_dark", umbral));
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isLight(){
        return isLight(128);
    }

    /**
     * @param umbral
     * @return CheckerColor
     */
    public CheckerColor isLight(double umbral){
        Predicate<Color> predicate = color -> {
            double luminosidad = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
            return luminosidad > umbral;
        };

        return is(predicate, sendMessage(INIT_COLOR, "is_light", umbral));
    }

    /**
     * @return CheckerColor
     */
    public CheckerColor isTransparent(){
        return is(color -> color.getAlpha() < 255, sendMessage(INIT_COLOR, "is_transparent"));
    }

    /**
     * El umbral por defecto es 128
     * @param other
     * @return
     */
    public CheckerColor hasContrast(Color other){
        return hasContrast(other, 128);
    }

    /**
     * @param other
     * @param umbral
     * @return CheckerColor
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
     * @param alpha
     * @return CheckerColor
     */
    public CheckerColor hasAlpha(int alpha){
        return is(color -> color.getAlpha() == alpha, sendMessage(INIT_COLOR, "has_alpha", alpha));
    }

    /**
     * @param umbral
     * @return CheckerColor
     */
    public CheckerColor isDesaturated(double umbral) {
        Predicate<Color> predicate = color ->{
            float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            return hsb[1] < umbral;
        };
        return is(predicate, sendMessage(INIT_COLOR, "is_desaturated"));
    }

    /**
     * @param other
     * @param umbral
     * @return CheckerColor
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
     * @return CheckerColor
     */
    public CheckerColor isOpaque(){
        return is(color -> color.getAlpha() == 255, sendMessage(INIT_COLOR, "is_opaque"));
    }

    /**
     * @param hex
     * @return CheckerColor
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
