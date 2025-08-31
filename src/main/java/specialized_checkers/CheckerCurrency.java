package specialized_checkers;

import static util.Message.*;

import java.util.Currency;
import java.util.Locale;

import util.AbstractChecker;

/**
 * CheckerCurrency is a specialized checker for validating and performing assertions on {@link Currency} values.
 * <p>
 * It provides a fluent API for common currency validations such as checking symbols, fraction digits, and locale association.
 */
public class CheckerCurrency extends AbstractChecker<Currency, CheckerCurrency> {

    private static final String INIT_CURRENCY = "currency";
    private static final String DEFAULT_NAME = "Currency";

    /**
     * Constructs a new {@code CheckerCurrency} with the specified currency and name.
     *
     * @param currency the {@link Currency} to be associated with this checker
     * @param name the name of the checker
     */
    protected CheckerCurrency(Currency currency, String name) {
        super(currency, name);
    }

    /**
     * Creates a new CheckerCurrency instance for the given currency and name.
     *
     * @param currency the currency value to be checked
     * @param name     the name to identify the currency in error messages
     * @return a CheckerCurrency instance for further validations
     */
    public static CheckerCurrency check(Currency currency, String name) {
        return new CheckerCurrency(currency, name);
    }

    /**
     * Creates a new CheckerCurrency instance for the given currency with a default name.
     *
     * @param currency the currency value to be checked
     * @return a CheckerCurrency instance for further validations
     */
    public static CheckerCurrency check(Currency currency) {
        return check(currency, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API usage).
     *
     * @return this CheckerCurrency instance
     */
    @Override
    protected CheckerCurrency self() {
        return this;
    }

    /**
     * Checks if the currency has the specified symbol in the default locale.
     *
     * @param symbol the symbol to compare with the currency's symbol
     * @return this CheckerCurrency instance for chaining
     */
    public CheckerCurrency hasSymbol(String symbol){
        return is(currency -> currency.getSymbol().equals(symbol), sendMessage(INIT_CURRENCY, "has_symbol", symbol));
    }

    /**
     * Checks if the currency has the specified symbol in the given locale.
     *
     * @param symbol the symbol to compare with the currency's symbol
     * @param locale the locale to use for symbol lookup
     * @return this CheckerCurrency instance for chaining
     */
    public CheckerCurrency hasSymbol(String symbol, Locale locale){
        return is(currency -> currency.getSymbol(locale).equals(symbol), sendMessage(INIT_CURRENCY, "has_symbol.locale", symbol, locale));
    }

    /**
     * Checks if the currency has the specified number of default fraction digits.
     *
     * @param n the expected number of fraction digits
     * @return this CheckerCurrency instance for chaining
     */
    public CheckerCurrency withDefaultFractionDigits(int n){
        return is(currency -> currency.getDefaultFractionDigits() == n, sendMessage(INIT_CURRENCY, "with_default_fraction_digits", n));
    }

    /**
     * Checks if the currency is the one used in the specified locale.
     *
     * @param locale the locale to check against
     * @return this CheckerCurrency instance for chaining
     */
    public CheckerCurrency isFrom(Locale locale){
        return is(currency -> Currency.getInstance(locale).equals(currency), sendMessage(INIT_CURRENCY, "is_from", locale));
    }

}
