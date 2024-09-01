package specialized_checkers.number;

import util.AbstractChecker;

public class CheckerLong extends AbstractChecker<Long> implements InterfaceCheckerNumber<CheckerLong> {

    public CheckerLong(Long object, String name) {
        super(object, name);
    }

    public CheckerLong isPositive() {
        is(this.object > 0, "null");
        return this;
    }

    public CheckerLong isPositiveOrZero() {
        is(this.object >= 0, "null");
        return this;
    }

    public CheckerLong isNegative() {
        is(this.object < 0, "null");
        return this;
    }

    public CheckerLong isNegativeOrZero() {
        is(this.object <= 0, "null");
        return this;
    }

    public CheckerLong isZero() {
        is(this.object == 0, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Byte number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Short number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Integer number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Long number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Float number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterThan(Double number) {
        is(this.object > number, "null");
        return this;
    }
    

    @Override
    public CheckerLong isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Short number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Long number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Float number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerLong isGreaterOrEqualTo(Double number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessThan(Byte number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessThan(Short number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessThan(Integer number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessThan(Long number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessThan(Float number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessThan(Double number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Byte number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Short number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Integer number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Long number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Float number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerLong isLessOrEqualTo(Double number) {
        is(this.object <= number, "null");
        return this;
    }
}
