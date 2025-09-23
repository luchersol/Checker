package util;

public class CheckerTest {

    public static void main(String[] args) throws CheckerException {
        Integer d = 1;
        Checker.check(d)
            .saveErrors()
            .isInteger()
            .isGreaterOrEqualTo(0)
            .end()
            .checkProperty("intValue")
            .isEqual(d)
            .show();
    }
}
