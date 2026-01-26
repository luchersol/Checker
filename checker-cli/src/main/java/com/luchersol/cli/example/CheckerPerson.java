package com.luchersol.cli.example;

import com.luchersol.core.util.AbstractChecker;
import java.lang.Override;
import java.lang.String;

public class CheckerPerson extends AbstractChecker<Person, CheckerPerson> {
    protected CheckerPerson(Person obj, String name) {
        super(obj, name);
    }

    @Override
    protected CheckerPerson self() {
        return this;
    }

    public CheckerPerson check(Person obj, String name) {
        return new CheckerPerson(obj, name);
    }

    public CheckerPerson hasName() {
        return is(obj -> obj.hasName(), "Calling method hasName() returned false");
    }

    public CheckerPerson isGreaterAge(int arg0) {
        return is(obj -> obj.isGreaterAge(arg0), "Calling method isGreaterAge(int) returned false");
    }
}
