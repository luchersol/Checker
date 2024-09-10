package specialized_checkers;

import static util.Message.sendMessage;

import java.util.Currency;
import java.util.Locale;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerCurrency extends AbstractChecker<Currency> {

    private static final String INIT_CURRENCY = "currency";

    public CheckerCurrency(Currency object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerCurrency is(Predicate<Currency> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerCurrency is(Predicate<Currency> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerCurrency isNot(Predicate<Currency> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerCurrency isNot(Predicate<Currency> condition) {
        super.isNot(condition);
        return this;
    }
    
    public CheckerCurrency hasSymbol(String symbol){
        return is(currency -> currency.getSymbol().equals(symbol), sendMessage(INIT_CURRENCY, "has_symbol"));
    }

    public CheckerCurrency withDefaultFractionDigits(int n){
        return is(currency -> currency.getDefaultFractionDigits() == n, sendMessage(INIT_CURRENCY, "with_default_fraction_digits", n));
    }

    public CheckerCurrency isFrom(Locale locale){
        return is(currency -> Currency.getInstance(locale).equals(currency), sendMessage(INIT_CURRENCY, "is_from", locale));
    }

}
