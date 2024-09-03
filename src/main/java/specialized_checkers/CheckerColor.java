package specialized_checkers;

import static util.Message.sendMessage;

import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;
import java.awt.Color;

public class CheckerColor extends AbstractChecker<Color> {

    private static final String INIT_COLOR = "color";

    public CheckerColor(Color object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerColor is(Predicate<Color> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerColor is(Predicate<Color> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerColor isNot(Predicate<Color> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerColor isNot(Predicate<Color> condition) {
        super.isNot(condition);
        return this;
    }

}
