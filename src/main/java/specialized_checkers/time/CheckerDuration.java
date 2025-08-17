package specialized_checkers.time;

import static util.Message.*;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerDuration extends AbstractChecker<Duration> {

    // private static final String INIT_TIME = "time";
    private static final String INIT_DURATION = "time.duration";

    public CheckerDuration(Duration object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerDuration is(Predicate<Duration> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerDuration is(Predicate<Duration> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerDuration isNot(Predicate<Duration> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerDuration isNot(Predicate<Duration> condition) {
        super.isNot(condition);
        return this;
    } 

    public CheckerDuration isPositive(){
        return is(duration -> !duration.isNegative() && !duration.isZero(), sendMessage(INIT_DURATION, "is_positive"));
    }
    
    public CheckerDuration isNegative(){
        return is(duration -> duration.isNegative(), sendMessage(INIT_DURATION, "is_negative"));
    }
    
    public CheckerDuration isZero(){
        return is(duration -> duration.isZero(), sendMessage(INIT_DURATION, "is_zero"));
    }

    public CheckerDuration isGreaterThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) > time, sendMessage(INIT_DURATION, "is_greater_than"));
    }

    public CheckerDuration isGreaterOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) >= time, sendMessage(INIT_DURATION, "is_greater_or_equal_than"));
    }

    public CheckerDuration isLessThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) < time, sendMessage(INIT_DURATION, "is_less_than"));
    }

    public CheckerDuration isLessOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) <= time, sendMessage(INIT_DURATION, "is_less_or_equal_than"));
    }

    public CheckerDuration isEqual(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) == time, sendMessage(INIT_DURATION, "is_equal"));
    }

    public CheckerDuration isGreaterThan(Duration other){
        return is(duration -> duration.compareTo(other) > 0, sendMessage(INIT_DURATION, "is_greater_than"));
    }

    public CheckerDuration isGreaterOrEqualThan(Duration other){
        return is(duration -> duration.compareTo(other) >= 0, sendMessage(INIT_DURATION, "is_greater_or_equal_than"));
    }

    public CheckerDuration isLessThan(Duration other){
        return is(duration -> duration.compareTo(other) < 0, sendMessage(INIT_DURATION, "is_less_than"));
    }

    public CheckerDuration isLessOrEqualThan(Duration other){
        return is(duration -> duration.compareTo(other) <= 0, sendMessage(INIT_DURATION, "is_less_or_equal_than"));
    }

    public CheckerDuration isEqual(Duration other){
        return is(duration -> duration.compareTo(other) == 0, sendMessage(INIT_DURATION, "is_equal"));
    }

}
