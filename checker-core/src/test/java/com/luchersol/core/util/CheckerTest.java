package com.luchersol.core.util;

import com.luchersol.core.util.Persona.InnerPerson;

public class CheckerTest {

    public static void main(String[] args) throws CheckerException {
        Persona persona = new Persona(
            new InnerPerson("Lucas", 23)
        );

        Checker<?> check = Checker.check(persona.getInnerPerson(), "persona")
            .checkProperty(InnerPerson::getName, "name")
            .saveErrors()
            .isNumber()
            .isString()
            .isDigit()
            .end();

        check.show();
    }

}
