package com.luchersol.core.util;

public class Regex {

    public static final String SPECIAL_CHARACTERS = ".*[!@#$%^&*(),.?\":{}|<>].*";
    public static final String DIGIT = "\\d+";
    public static final String HEXADECIMAL = "^(0[xX])?[0-9A-Fa-f]+$";
    public static final String DNI = "\\d{8}[A-Z]";
    public static final String IPV4 = "^(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])(\\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}$";
    public static final String IPV6 = "^(([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})|(([0-9a-fA-F]{1,4}:){1,7}|:):(([0-9a-fA-F]{1,4}:){1,6}[0-9a-fA-F]{1,4})?)$";

}
