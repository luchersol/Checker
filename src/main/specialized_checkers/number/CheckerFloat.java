package src.main.specialized_checkers.number;

import src.main.AbstractChecker;

public class CheckerFloat extends AbstractChecker<Float> implements InterfaceCheckerNumber<CheckerFloat>{

    public CheckerFloat(Float object, String name) {
        super(object, name);
    }

    public CheckerFloat isNaN(){
        is(this.object.isNaN(), null);
        return this;
    }

    public CheckerFloat isInfinite(){
        is(this.object.isInfinite(), null);
        return this;
    }

    public CheckerFloat isPositive() {
        is(this.object > 0, "null");
        return this;
    }

    public CheckerFloat isPositiveOrZero() {
        is(this.object >= 0, "null");
        return this;
    }

    public CheckerFloat isNegative() {
        is(this.object < 0, "null");
        return this;
    }

    public CheckerFloat isNegativeOrZero() {
        is(this.object <= 0, "null");
        return this;
    }

    public CheckerFloat isZero() {
        is(this.object == 0, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Byte number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Short number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Integer number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Long number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Float number) {
        is(this.object > number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterThan(Double number) {
        is(this.object > number, "null");
        return this;
    }
    

    @Override
    public CheckerFloat isGreaterOrEqualTo(Byte number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Short number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Integer number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Long number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Float number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isGreaterOrEqualTo(Double number) {
        is(this.object >= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Byte number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Short number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Integer number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Long number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Float number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessThan(Double number) {
        is(this.object < number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Byte number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Short number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Integer number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Long number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Float number) {
        is(this.object <= number, "null");
        return this;
    }

    @Override
    public CheckerFloat isLessOrEqualTo(Double number) {
        is(this.object <= number, "null");
        return this;
    }

    
}
