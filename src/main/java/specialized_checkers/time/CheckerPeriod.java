package specialized_checkers.time;

import static util.Message.*;

import java.time.Period;
import java.time.temporal.TemporalUnit;

import util.AbstractChecker;

public class CheckerPeriod extends AbstractChecker<Period, CheckerPeriod> {

    // private static final String INIT_TIME = "time";
    private static final String INIT_PERIOD = "time.period";

    public CheckerPeriod(Period object, String name) {
        super(object, name);
    }

    @Override
    protected CheckerPeriod self() {
        return this;
    }

    public CheckerPeriod isPositive(){
        return is(period -> !period.isNegative() && !period.isZero(), sendMessage(INIT_PERIOD, "is_positive"));
    }

    public CheckerPeriod isNegative(){
        return is(period -> period.isNegative(), sendMessage(INIT_PERIOD, "is_negative"));
    }

    public CheckerPeriod isZero(){
        return is(period -> period.isZero(), sendMessage(INIT_PERIOD, "is_zero"));
    }

    public CheckerPeriod isGreaterThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) > time, sendMessage(INIT_PERIOD, "is_greater_than"));
    }

    public CheckerPeriod isGreaterOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) >= time, sendMessage(INIT_PERIOD, "is_greater_or_equal_than"));
    }

    public CheckerPeriod isLessThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) < time, sendMessage(INIT_PERIOD, "is_less_than"));
    }

    public CheckerPeriod isLessOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) <= time, sendMessage(INIT_PERIOD, "is_less_or_equal_than"));
    }

    public CheckerPeriod isEqual(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) == time, sendMessage(INIT_PERIOD, "is_equal"));
    }

    private static int comparateTo(Period self, Period other){
        if(self.equals(other))
            return 0;
        if(self.getYears() > other.getYears())
            return 1;
        else if(self.getMonths() > other.getMonths())
            return 1;
        else if(self.getDays() > other.getDays())
            return 1;
        else
            return -1;
    }

    public CheckerPeriod isGreaterThan(Period other){
        return is(period -> comparateTo(period, other) > 0, sendMessage(INIT_PERIOD, "is_greater_than"));
    }

    public CheckerPeriod isGreaterOrEqualThan(Period other){
        return is(period -> comparateTo(period, other) >= 0, sendMessage(INIT_PERIOD, "is_greater_or_equal_than"));
    }

    public CheckerPeriod isLessThan(Period other){
        return is(period -> comparateTo(period, other) < 0, sendMessage(INIT_PERIOD, "is_less_than"));
    }

    public CheckerPeriod isLessOrEqualThan(Period other){
        return is(period -> comparateTo(period, other) <= 0, sendMessage(INIT_PERIOD, "is_less_or_equal_than"));
    }

    public CheckerPeriod isEqual(Period other){
        return is(period -> comparateTo(period, other) == 0, sendMessage(INIT_PERIOD, "is_equal"));
    }

}
