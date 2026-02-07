package com.luchersol.core.specialized_checkers.io;

import static com.luchersol.core.util.MessageService.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

import com.luchersol.core.util.AbstractChecker;

/**
 * A specialized checker for {@link URI} objects, providing fluent validation methods
 * for various URI components such as scheme, host, port, authority, fragment, path, and query.
 * <p>
 * This class extends {@code AbstractChecker} to enable chainable assertions on {@code URI} instances.
 * It supports both default and custom naming for checkers, and provides static factory methods
 * for convenient instantiation from {@code URI} or {@code String} representations.
 * </p>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * CheckerURI checker = CheckerURI.check("https://example.com")
 *     .isAbsolute()
 *     .hasHost("example.com")
 *     .hasPath("/index.html");
 * }</pre>
 *
 * <p>
 * Each validation method returns {@code this} for fluent chaining.
 * </p>
 */
public class CheckerURI extends AbstractChecker<URI, CheckerURI> {

    private static final String INIT_URI = "io.uri";
    private static final String DEFAULT_NAME = "Uri";

    /**
     * Constructs a new {@code CheckerURI} with the specified {@link URI} and name.
     *
     * @param uri  the URI associated with this checker
     * @param name the name of the checker
     */
    protected CheckerURI(URI uri, String name) {
        super(uri, name);
    }

    /**
     * Creates a CheckerURI for the given URI and assigns a custom name.
     *
     * @param uri the URI to check
     * @param name the name to assign to this checker
     * @return a CheckerURI instance for the given URI
     */
    public static CheckerURI check(URI uri, String name) {
        return new CheckerURI(uri, name);
    }

    /**
     * Creates a CheckerURI from a string path and assigns a custom name.
     *
     * @param path the string representation of the URI
     * @param name the name to assign to this checker
     * @return a CheckerURI instance for the given path
     */
    public static CheckerURI check(String path, String name) {
        URI uri = URI.create(path);
        return new CheckerURI(uri, name);
    }

    /**
     * Creates a CheckerURI for the given URI with a default name.
     *
     * @param uri the URI to check
     * @return a CheckerURI instance for the given URI
     */
    public static CheckerURI check(URI uri) {
        return check(uri, DEFAULT_NAME);
    }

    /**
     * Creates a CheckerURI from a string path with a default name.
     *
     * @param path the string representation of the URI
     * @return a CheckerURI instance for the given path
     */
    public static CheckerURI check(String path) {
        return check(path, DEFAULT_NAME);
    }

    /**
     * Returns this instance (for fluent API).
     *
     * @return this CheckerURI instance
     */
    @Override
    protected CheckerURI self() {
        return this;
    }

    /**
     * Checks if the URI is absolute (has a scheme).
     *
     * @return this CheckerURI instance
     */
    public CheckerURI isAbsolute() {
        return is(uri -> uri.isAbsolute(), sendMessage(INIT_URI, "is_absolute", this.object));
    }

    /**
     * Checks if the URI is relative (does not have a scheme).
     *
     * @return this CheckerURI instance
     */
    public CheckerURI isRelative() {
        return is(uri -> !uri.isAbsolute(), sendMessage(INIT_URI, "is_relative", this.object));
    }

    /**
     * Checks if the URI has a scheme component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasSqueme() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_squeme", this.object));
    }

    /**
     * Checks if the URI's scheme matches any of the specified schemes.
     *
     * @param squemes the schemes to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasSqueme(String... squemes) {
        return is(uri -> Arrays.stream(squemes).anyMatch(squeme -> Objects.equals(uri.getScheme(), squeme)),
                sendMessage(INIT_URI, "has_squeme.param", this.object, Arrays.toString(squemes)));
    }

    /**
     * Checks if the URI has a host component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasHost() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_host", this.object));
    }

    /**
     * Checks if the URI's host matches any of the specified hosts.
     *
     * @param hosts the hosts to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasHost(String... hosts) {
        return is(uri -> Arrays.stream(hosts).anyMatch(host -> Objects.equals(uri.getHost(), host)),
                sendMessage(INIT_URI, "has_host.param", this.object, Arrays.toString(hosts)));
    }

    /**
     * Checks if the URI has a port component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasPort() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_port", this.object));
    }

    /**
     * Checks if the URI's port matches any of the specified ports.
     *
     * @param ports the ports to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasPort(int... ports) {
        return is(uri -> Arrays.stream(ports).anyMatch(port -> Objects.equals(uri.getPort(), port)),
                sendMessage(INIT_URI, "has_port.port", this.object, Arrays.toString(ports)));
    }

    /**
     * Checks if the URI has an authority component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasAuthority() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_authority", this.object));
    }

    /**
     * Checks if the URI's authority matches any of the specified authorities.
     *
     * @param authorities the authorities to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasAuthority(String... authorities) {
        return is(
                uri -> Arrays.stream(authorities).anyMatch(authority -> Objects.equals(uri.getAuthority(), authority)),
                sendMessage(INIT_URI, "has_authority.param", this.object, Arrays.toString(authorities)));
    }

    /**
     * Checks if the URI has a fragment component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasFrament() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_fragment", this.object));
    }

    /**
     * Checks if the URI's fragment matches any of the specified fragments.
     *
     * @param fragments the fragments to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasFrament(String... fragments) {
        return is(uri -> Arrays.stream(fragments).anyMatch(fragment -> Objects.equals(uri.getFragment(), fragment)),
                sendMessage(INIT_URI, "has_fragment.param", this.object, Arrays.toString(fragments)));
    }

    /**
     * Checks if the URI has a path component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasPath() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_path", this.object));
    }

    /**
     * Checks if the URI's path matches any of the specified paths.
     *
     * @param paths the paths to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasPath(String... paths) {
        return is(uri -> Arrays.stream(paths).anyMatch(path -> Objects.equals(uri.getPath(), path)),
                sendMessage(INIT_URI, "has_path.param", this.object, Arrays.toString(paths)));
    }

    /**
     * Checks if the URI has a query component.
     *
     * @return this CheckerURI instance
     */
    public CheckerURI hasQuery() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_query", this.object));
    }

    /**
     * Checks if the URI's query matches any of the specified queries.
     *
     * @param queries the queries to check for
     * @return this CheckerURI instance
     */
    public CheckerURI hasQuery(String... queries) {
        return is(uri -> Arrays.stream(queries).anyMatch(query -> Objects.equals(uri.getQuery(), query)),
                sendMessage(INIT_URI, "has_query.param", this.object, Arrays.toString(queries)));
    }

}
