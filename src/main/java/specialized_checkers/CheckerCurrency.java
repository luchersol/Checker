package specialized_checkers;

import static util.Message.*;

import java.util.Currency;
import java.util.Locale;

import util.AbstractChecker;

public class CheckerCurrency extends AbstractChecker<Currency, CheckerCurrency> {

    private static final String INIT_CURRENCY = "currency";
    private static final String DEFAULT_NAME = "Currency";

    protected CheckerCurrency(Currency currency, String name) {
        super(currency, name);
    }

    /**
     * @param currency
     * @param name
     * @return CheckerCurrency
     */
    public static CheckerCurrency check(Currency currency, String name) {
        return new CheckerCurrency(currency, name);
    }

    /**
     * @param currency
     * @return CheckerCurrency
     */
    public static CheckerCurrency check(Currency currency) {
        return check(currency, DEFAULT_NAME);
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
        return is(currency -> currency.getSymbol().equals(symbol), sendMessage(INIT_CURRENCY, "has_symbol", symbol));
    }

    /**
     * @param symbol
     * @param locale
     * @return CheckerCurrency
     */
    public CheckerCurrency hasSymbol(String symbol, Locale locale){
        return is(currency -> currency.getSymbol(locale).equals(symbol), sendMessage(INIT_CURRENCY, "has_symbol.locale", symbol, locale));
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
