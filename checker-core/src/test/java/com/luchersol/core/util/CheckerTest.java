package com.luchersol.core.util;

public class CheckerTest {

    public static void main(String[] args) throws CheckerException {
        Integer num = 1;
        Checker.check(num, "num")
            .saveErrors()
            .isInteger()
            .isGreaterOrEqualTo(0)
            .end()
            .checkProperty("intValue()")
            .isEqual(num)
            .show();
    }
}
