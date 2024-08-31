package src.main.specialized_checkers.number;

import java.util.List;

import src.main.AbstractChecker;

public class CheckerInteger extends AbstractChecker<Integer> implements InterfaceCheckerNumber<CheckerInteger> {

    public CheckerInteger(Integer object, String name) {
        super(object, name);
    }

    public CheckerInteger isPositive() {
        is(this.object > 0, "null");
        return this;
    }

    public CheckerInteger isPositiveOrZero() {
        is(this.object >= 0, "null");
        return this;
    }

    public CheckerInteger isNegative() {
        is(this.object < 0, "null");
        return this;
    }

    public CheckerInteger isNegativeOrZero() {
        is(this.object <= 0, "null");
        return this;
    }

    public CheckerInteger isZero() {
        is(this.object == 0, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Byte number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Short number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Integer number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Long number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Float number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterThan(Double number) {
        is(this.object > number, "null");
        return this;
    }
    

    @Override
    public CheckerInteger isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Short number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Long number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Float number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isGreaterOrEqualTo(Double number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Byte number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Short number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Integer number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Long number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Float number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessThan(Double number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Byte number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Short number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Integer number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Long number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Float number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerInteger isLessOrEqualTo(Double number) {
        is(this.object <= number, "null");
        return this;
    }


    
}
