package specialized_checkers.number;

import util.AbstractChecker;

public class CheckerDouble extends AbstractChecker<Double> implements InterfaceCheckerNumber<CheckerDouble> {

    public CheckerDouble(Double object, String name) {
        super(object, name);
    }
    
    public CheckerDouble isNaN(){
        is(this.object.isNaN(), null);
        return this;
    }

    public CheckerDouble isInfinite(){
        is(this.object.isInfinite(), null);
        return this;
    }

    public CheckerDouble isPositive() {
        is(this.object > 0, "null");
        return this;
    }

    public CheckerDouble isPositiveOrZero() {
        is(this.object >= 0, "null");
        return this;
    }

    public CheckerDouble isNegative() {
        is(this.object < 0, "null");
        return this;
    }

    public CheckerDouble isNegativeOrZero() {
        is(this.object <= 0, "null");
        return this;
    }

    public CheckerDouble isZero() {
        is(this.object == 0, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Byte number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Short number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Integer number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Long number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Float number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterThan(Double number) {
        is(this.object > number, "null");
        return this;
    }
    

    @Override
    public CheckerDouble isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Short number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Long number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Float number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isGreaterOrEqualTo(Double number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Byte number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Short number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Integer number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Long number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Float number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessThan(Double number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Byte number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Short number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Integer number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Long number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Float number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerDouble isLessOrEqualTo(Double number) {
        is(this.object <= number, "null");
        return this;
    }
}
