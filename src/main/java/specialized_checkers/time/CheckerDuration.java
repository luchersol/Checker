package specialized_checkers.time;

import static util.Message.*;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

import util.AbstractChecker;

public class CheckerDuration extends AbstractChecker<Duration, CheckerDuration> {

    // private static final String INIT_TIME = "time";
    private static final String INIT_DURATION = "time.duration";

    public CheckerDuration(Duration object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerDuration
     */
    @Override
    protected CheckerDuration self() {
        return this;
    }

    /**
     * @return CheckerDuration
     */
    public CheckerDuration isPositive(){
        return is(duration -> !duration.isNegative() && !duration.isZero(), sendMessage(INIT_DURATION, "is_positive"));
    }

    /**
     * @return CheckerDuration
     */
    public CheckerDuration isNegative(){
        return is(duration -> duration.isNegative(), sendMessage(INIT_DURATION, "is_negative"));
    }

    /**
     * @return CheckerDuration
     */
    public CheckerDuration isZero(){
        return is(duration -> duration.isZero(), sendMessage(INIT_DURATION, "is_zero"));
    }

    /**
     * @param temporalUnit
     * @param time
     * @return CheckerDuration
     */
    public CheckerDuration isGreaterThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) > time, sendMessage(INIT_DURATION, "is_greater_than"));
    }

    /**
     * @param temporalUnit
     * @param time
     * @return CheckerDuration
     */
    public CheckerDuration isGreaterOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) >= time, sendMessage(INIT_DURATION, "is_greater_or_equal_than"));
    }

    /**
     * @param temporalUnit
     * @param time
     * @return CheckerDuration
     */
    public CheckerDuration isLessThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) < time, sendMessage(INIT_DURATION, "is_less_than"));
    }

    /**
     * @param temporalUnit
     * @param time
     * @return CheckerDuration
     */
    public CheckerDuration isLessOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) <= time, sendMessage(INIT_DURATION, "is_less_or_equal_than"));
    }

    /**
     * @param temporalUnit
     * @param time
     * @return CheckerDuration
     */
    public CheckerDuration isEqual(TemporalUnit temporalUnit, double time){
        return is(duration -> duration.get(temporalUnit) == time, sendMessage(INIT_DURATION, "is_equal"));
    }

    /**
     * @param other
     * @return CheckerDuration
     */
    public CheckerDuration isGreaterThan(Duration other){
        return is(duration -> duration.compareTo(other) > 0, sendMessage(INIT_DURATION, "is_greater_than"));
    }

    /**
     * @param other
     * @return CheckerDuration
     */
    public CheckerDuration isGreaterOrEqualThan(Duration other){
        return is(duration -> duration.compareTo(other) >= 0, sendMessage(INIT_DURATION, "is_greater_or_equal_than"));
    }

    /**
     * @param other
     * @return CheckerDuration
     */
    public CheckerDuration isLessThan(Duration other){
        return is(duration -> duration.compareTo(other) < 0, sendMessage(INIT_DURATION, "is_less_than"));
    }

    /**
     * @param other
     * @return CheckerDuration
     */
    public CheckerDuration isLessOrEqualThan(Duration other){
        return is(duration -> duration.compareTo(other) <= 0, sendMessage(INIT_DURATION, "is_less_or_equal_than"));
    }

    /**
     * @param other
     * @return CheckerDuration
     */
    public CheckerDuration isEqual(Duration other){
        return is(duration -> duration.compareTo(other) == 0, sendMessage(INIT_DURATION, "is_equal"));
    }

}
