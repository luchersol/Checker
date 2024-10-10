package specialized_checkers.time;

import static util.Message.sendMessage;

import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class Checker_period extends AbstractChecker<Period> {

    private static final String INIT_TIME = "time";
    private static final String INIT_PERIOD = "time.period";

    public Checker_period(Period object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public Checker_period is(Predicate<Period> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public Checker_period is(Predicate<Period> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public Checker_period isNot(Predicate<Period> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public Checker_period isNot(Predicate<Period> condition) {
        super.isNot(condition);
        return this;
    }

    public Checker_period isPositive(){
        return is(period -> !period.isNegative() && !period.isZero(), sendMessage(INIT_PERIOD, "is_positive"));
    }
    
    public Checker_period isNegative(){
        return is(period -> period.isNegative(), sendMessage(INIT_PERIOD, "is_negative"));
    }
    
    public Checker_period isZero(){
        return is(period -> period.isZero(), sendMessage(INIT_PERIOD, "is_zero"));
    }

    public Checker_period isGreaterThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) > time, sendMessage(INIT_PERIOD, "is_greater_than"));
    }

    public Checker_period isGreaterOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) >= time, sendMessage(INIT_PERIOD, "is_greater_or_equal_than"));
    }

    public Checker_period isLessThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) < time, sendMessage(INIT_PERIOD, "is_less_than"));
    }

    public Checker_period isLessOrEqualThan(TemporalUnit temporalUnit, double time){
        return is(period -> period.get(temporalUnit) <= time, sendMessage(INIT_PERIOD, "is_less_or_equal_than"));
    }

    public Checker_period isEqual(TemporalUnit temporalUnit, double time){
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

    public Checker_period isGreaterThan(Period other){
        return is(period -> comparateTo(period, other) > 0, sendMessage(INIT_PERIOD, "is_greater_than"));
    }

    public Checker_period isGreaterOrEqualThan(Period other){
        return is(period -> comparateTo(period, other) >= 0, sendMessage(INIT_PERIOD, "is_greater_or_equal_than"));
    }

    public Checker_period isLessThan(Period other){
        return is(period -> comparateTo(period, other) < 0, sendMessage(INIT_PERIOD, "is_less_than"));
    }

    public Checker_period isLessOrEqualThan(Period other){
        return is(period -> comparateTo(period, other) <= 0, sendMessage(INIT_PERIOD, "is_less_or_equal_than"));
    }

    public Checker_period isEqual(Period other){
        return is(period -> comparateTo(period, other) == 0, sendMessage(INIT_PERIOD, "is_equal"));
    }

}
