package specialized_checkers.io;

import static util.Message.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;

import util.AbstractChecker;

public class CheckerURI extends AbstractChecker<URI, CheckerURI> {

    private static final String INIT_URI = "io.uri";

    public CheckerURI(URI object, String name) {
        super(object, name);
    }

    /**
     * @return CheckerURI
     */
    @Override
    protected CheckerURI self() {
        return this;
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI isAbsolute() {
        return is(uri -> uri.isAbsolute(), sendMessage(INIT_URI, "is_absolute", this.object));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI isRelative() {
        return is(uri -> !uri.isAbsolute(), sendMessage(INIT_URI, "is_relative", this.object));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasSqueme() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_squeme", this.object));
    }

    /**
     * @param squemes
     * @return CheckerURI
     */
    public CheckerURI hasSqueme(String... squemes) {
        return is(uri -> Arrays.stream(squemes).anyMatch(squeme -> Objects.equals(uri.getScheme(), squeme)),
                sendMessage(INIT_URI, "has_squeme.param", this.object, Arrays.toString(squemes)));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasHost() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_host", this.object));
    }

    /**
     * @param hosts
     * @return CheckerURI
     */
    public CheckerURI hasHost(String... hosts) {
        return is(uri -> Arrays.stream(hosts).anyMatch(host -> Objects.equals(uri.getHost(), host)),
                sendMessage(INIT_URI, "has_host.param", this.object, Arrays.toString(hosts)));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasPort() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_port", this.object));
    }

    /**
     * @param ports
     * @return CheckerURI
     */
    public CheckerURI hasPort(int... ports) {
        return is(uri -> Arrays.stream(ports).anyMatch(port -> Objects.equals(uri.getPort(), port)),
                sendMessage(INIT_URI, "has_port.port", this.object, Arrays.toString(ports)));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasAuthority() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_authority", this.object));
    }

    /**
     * @param authorities
     * @return CheckerURI
     */
    public CheckerURI hasAuthority(String... authorities) {
        return is(
                uri -> Arrays.stream(authorities).anyMatch(authority -> Objects.equals(uri.getAuthority(), authority)),
                sendMessage(INIT_URI, "has_authority.param", this.object, Arrays.toString(authorities)));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasFrament() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_fragment", this.object));
    }

    /**
     * @param fragments
     * @return CheckerURI
     */
    public CheckerURI hasFrament(String... fragments) {
        return is(uri -> Arrays.stream(fragments).anyMatch(fragment -> Objects.equals(uri.getFragment(), fragment)),
                sendMessage(INIT_URI, "has_fragment.param", this.object, Arrays.toString(fragments)));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasPath() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_path", this.object));
    }

    /**
     * @param paths
     * @return CheckerURI
     */
    public CheckerURI hasPath(String... paths) {
        return is(uri -> Arrays.stream(paths).anyMatch(path -> Objects.equals(uri.getPath(), path)),
                sendMessage(INIT_URI, "has_path.param", this.object, Arrays.toString(paths)));
    }

    /**
     * @return CheckerURI
     */
    public CheckerURI hasQuery() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_query", this.object));
    }

    /**
     * @param queries
     * @return CheckerURI
     */
    public CheckerURI hasQuery(String... queries) {
        return is(uri -> Arrays.stream(queries).anyMatch(query -> Objects.equals(uri.getQuery(), query)),
                sendMessage(INIT_URI, "has_query.param", this.object, Arrays.toString(queries)));
    }

}
