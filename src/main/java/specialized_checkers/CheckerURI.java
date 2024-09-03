package specialized_checkers;

import static util.Message.sendMessage;

import java.net.URI;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

import util.AbstractChecker;
import util.ExceptionTracker;

public class CheckerURI extends AbstractChecker<URI> {

    private static final String INIT_URI = "uri";

    public CheckerURI(URI object, String name, ExceptionTracker exceptionTracker) {
        super(object, name, exceptionTracker);
    }

    @Override
    public CheckerURI is(Predicate<URI> condition, String message) {
        super.is(condition, message);
        return this;
    }

    @Override
    public CheckerURI is(Predicate<URI> condition) {
        super.is(condition);
        return this;
    }

    @Override
    public CheckerURI isNot(Predicate<URI> condition, String message) {
        super.isNot(condition, message);
        return this;
    }

    @Override
    public CheckerURI isNot(Predicate<URI> condition) {
        super.isNot(condition);
        return this;
    }

    public CheckerURI isAbsolute() {
        return is(uri -> uri.isAbsolute(), sendMessage(INIT_URI, "is_absolute"));
    }

    public CheckerURI isRelative() {
        return is(uri -> !uri.isAbsolute(), sendMessage(INIT_URI, "is_relative"));
    }

    public CheckerURI hasSqueme() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_squeme"));
    }

    public CheckerURI hasSqueme(String... squemes) {
        return is(uri -> Arrays.stream(squemes).anyMatch(squeme -> Objects.equals(uri.getScheme(), squeme)),
                sendMessage(INIT_URI, "has_squeme.param", Arrays.toString(squemes)));
    }

    public CheckerURI hasHost() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_host"));
    }

    public CheckerURI hasHost(String... hosts) {
        return is(uri -> Arrays.stream(hosts).anyMatch(host -> Objects.equals(uri.getHost(), host)),
                sendMessage(INIT_URI, "has_host.param", Arrays.toString(hosts)));
    }

    public CheckerURI hasPort() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_port"));
    }

    public CheckerURI hasPort(int... ports) {
        return is(uri -> Arrays.stream(ports).anyMatch(port -> Objects.equals(uri.getPort(), port)),
                sendMessage(INIT_URI, "has_port.port", Arrays.toString(ports)));
    }

    public CheckerURI hasAuthority() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_authority"));
    }

    public CheckerURI hasAuthority(String... authorities) {
        return is(
                uri -> Arrays.stream(authorities).anyMatch(authority -> Objects.equals(uri.getAuthority(), authority)),
                sendMessage(INIT_URI, "has_authority.param", Arrays.toString(authorities)));
    }

    public CheckerURI hasFrament() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_fragment"));
    }

    public CheckerURI hasFrament(String... fragments) {
        return is(uri -> Arrays.stream(fragments).anyMatch(fragment -> Objects.equals(uri.getFragment(), fragment)),
                sendMessage(INIT_URI, "has_fragment.param", Arrays.toString(fragments)));
    }

    public CheckerURI hasPath() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_path"));
    }

    public CheckerURI hasPath(String... paths) {
        return is(uri -> Arrays.stream(paths).anyMatch(path -> Objects.equals(uri.getPath(), path)),
                sendMessage(INIT_URI, "has_path.param", Arrays.toString(paths)));
    }

    public CheckerURI hasQuery() {
        return is(uri -> uri.getScheme() != null, sendMessage(INIT_URI, "has_query"));
    }

    public CheckerURI hasQuery(String... queries) {
        return is(uri -> Arrays.stream(queries).anyMatch(query -> Objects.equals(uri.getQuery(), query)),
                sendMessage(INIT_URI, "has_query.param", Arrays.toString(queries)));
    }

}
