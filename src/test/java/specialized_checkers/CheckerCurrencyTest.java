package specialized_checkers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Currency;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import util.CheckerException;

class CheckerCurrencyTest {

    @Test
    void hasSymbol_shouldPassForCorrectSymbol() {
        Currency usd = Currency.getInstance("USD");
        CheckerCurrency checker = new CheckerCurrency(usd, "usd");
        assertDoesNotThrow(() -> checker.hasSymbol("US$"));
    }

    @Test
    void hasSymbol_shouldFailForIncorrectSymbol() {
        Currency usd = Currency.getInstance("USD");
        CheckerCurrency checker = new CheckerCurrency(usd, "usd");
        assertThrows(CheckerException.class, () -> checker.hasSymbol("€"));
    }

    @Test
    void withDefaultFractionDigits_shouldPassForCorrectDigits() {
        Currency eur = Currency.getInstance("EUR");
        CheckerCurrency checker = new CheckerCurrency(eur, "eur");
        assertDoesNotThrow(() -> checker.withDefaultFractionDigits(2));
    }

    @Test
    void withDefaultFractionDigits_shouldFailForIncorrectDigits() {
        Currency eur = Currency.getInstance("EUR");
        CheckerCurrency checker = new CheckerCurrency(eur, "eur");
        assertThrows(CheckerException.class, () -> checker.withDefaultFractionDigits(0));
    }

    @Test
    void isFrom_shouldPassForMatchingLocale() {
        Currency jpy = Currency.getInstance("JPY");
        CheckerCurrency checker = new CheckerCurrency(jpy, "jpy");
        assertDoesNotThrow(() -> checker.isFrom(Locale.JAPAN));
    }

    @Test
    void isFrom_shouldFailForNonMatchingLocale() {
        Currency usd = Currency.getInstance("USD");
        CheckerCurrency checker = new CheckerCurrency(usd, "usd");
        assertThrows(CheckerException.class, () -> checker.isFrom(Locale.FRANCE));
    }
}
