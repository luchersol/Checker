/**
 * Specialized checker implementations for various data types and structures.
 *
 * <p>This package provides fluent API-based validators for common Java types including
 * strings, enums, currency values, colors, and complex mathematical structures.
 * Each specialized checker extends the base validation framework to provide type-specific
 * assertion methods.</p>
 *
 * <p>Typical usage pattern uses a fluent interface for method chaining:
 * <pre>{@code
 * CheckerString.check("value")
 *     .isNotEmpty()
 *     .minLength(5)
 *     .matches("[a-z]+");
 * }</pre>
 * </p>
 *
 * @author LucherSol
 * @version 1.0
 */
package com.luchersol.core.specialized_checkers;
