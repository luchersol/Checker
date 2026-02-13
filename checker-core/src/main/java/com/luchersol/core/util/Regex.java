package com.luchersol.core.util;

/**
 * Utility class containing commonly used regular expression patterns.
 *
 * <p>
 * This class provides reusable {@link String} constants representing validated
 * regex patterns for typical validation scenarios such as numbers, identifiers,
 * and network addresses.
 *
 *
 * <p>
 * All patterns are designed to be used with {@link java.util.regex.Pattern}
 * and {@link java.util.regex.Matcher}.
 *
 */
public final class Regex {

    /**
     * Matches strings containing at least one special character.
     *
     * <p>Special characters included:
     * <pre>! @ # $ % ^ &amp; * ( ) , . ? " : { } | &lt; &gt;</pre>
     *
     * <p>Example matches:
     * <ul>
     *   <li>{@code hello!}</li>
     *   <li>{@code pass@word}</li>
     * </ul>
     */
    public static final String SPECIAL_CHARACTERS = ".*[!@#$%^&*(),.?\":{}|<>].*";

    /**
     * Matches one or more digits.
     *
     * <p>Equivalent to:
     * <pre>{@code [0-9]+}</pre>
     *
     * <p>Example matches:
     * <ul>
     *   <li>{@code 123}</li>
     *   <li>{@code 007}</li>
     * </ul>
     */
    public static final String DIGIT = "\\d+";

    /**
     * Matches hexadecimal numbers, optionally prefixed with {@code 0x} or {@code 0X}.
     *
     * <p>Example matches:
     * <ul>
     *   <li>{@code FF}</li>
     *   <li>{@code 0x1A3F}</li>
     * </ul>
     */
    public static final String HEXADECIMAL = "^(0[xX])?[0-9A-Fa-f]+$";

    /**
     * Matches a Spanish DNI (Documento Nacional de Identidad).
     *
     * <p>Format:
     * <ul>
     *   <li>8 digits</li>
     *   <li>1 uppercase letter</li>
     * </ul>
     *
     * <p>Example match:
     * <pre>{@code 12345678Z}</pre>
     *
     * <p><strong>Note:</strong> This pattern validates only the format,
     * not the correctness of the control letter.
     */
    public static final String DNI = "\\d{8}[A-Z]";

    /**
     * Matches a valid IPv4 address.
     *
     * <p>Each octet must be between {@code 0} and {@code 255}.
     *
     * <p>Example matches:
     * <ul>
     *   <li>{@code 192.168.1.1}</li>
     *   <li>{@code 8.8.8.8}</li>
     * </ul>
     */
    public static final String IPV4 =
            "^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])"
          + "(\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}$";

    /**
     * Matches a valid IPv6 address (compressed and full forms).
     *
     * <p>Supports:
     * <ul>
     *   <li>Full notation</li>
     *   <li>Compressed notation (::)</li>
     * </ul>
     *
     * <p>Example matches:
     * <ul>
     *   <li>{@code 2001:0db8:85a3:0000:0000:8a2e:0370:7334}</li>
     *   <li>{@code 2001:db8::1}</li>
     * </ul>
     */
    public static final String IPV6 =
            "^(([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})"
          + "|(([0-9a-fA-F]{1,4}:){1,7}|:)"
          + ":(([0-9a-fA-F]{1,4}:){1,6}[0-9a-fA-F]{1,4})?)$";

    /**
     * Private constructor to prevent instantiation.
     */
    private Regex() {
        throw new UnsupportedOperationException("Utility class");
    }
}
