package specialized_checkers;

import static util.Message.*;

import java.util.Currency;
import java.util.Locale;

import util.AbstractChecker;

public class CheckerCurrency extends AbstractChecker<Currency, CheckerCurrency> {

    private static final String INIT_CURRENCY = "currency";

    public CheckerCurrency(Currency object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerCurrency
     */
    @Override
    protected CheckerCurrency self() {
        return this;
    }

    /**
     * @param symbol
     * @return CheckerCurrency
     */
    public CheckerCurrency hasSymbol(String symbol){
        return is(currency -> currency.getSymbol().equals(symbol), sendMessage(INIT_CURRENCY, "has_symbol"));
    }

    /**
     * @param n
     * @return CheckerCurrency
     */
    public CheckerCurrency withDefaultFractionDigits(int n){
        return is(currency -> currency.getDefaultFractionDigits() == n, sendMessage(INIT_CURRENCY, "with_default_fraction_digits", n));
    }

    /**
     * @param locale
     * @return CheckerCurrency
     */
    public CheckerCurrency isFrom(Locale locale){
        return is(currency -> Currency.getInstance(locale).equals(currency), sendMessage(INIT_CURRENCY, "is_from", locale));
    }

}
