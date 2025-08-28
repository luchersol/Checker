package specialized_checkers.io;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.Test;

class CheckerURITest {

    @Test
    void testIsAbsolute() {
        URI uri = URI.create("http://example.com");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").isAbsolute());
    }

    @Test
    void testIsRelative() {
        URI uri = URI.create("/path/to/resource");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").isRelative());
    }

    @Test
    void testHasSqueme() {
        URI uri = URI.create("ftp://example.com");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasSqueme());
    }

    @Test
    void testHasSquemeWithParam() {
        URI uri = URI.create("ftp://example.com");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasSqueme("ftp", "http"));
    }

    @Test
    void testHasHost() {
        URI uri = URI.create("http://example.com");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasHost());
    }

    @Test
    void testHasHostWithParam() {
        URI uri = URI.create("http://example.com");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasHost("example.com", "localhost"));
    }

    @Test
    void testHasPort() {
        URI uri = URI.create("http://example.com:8080");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasPort());
    }

    @Test
    void testHasPortWithParam() {
        URI uri = URI.create("http://example.com:8080");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasPort(8080, 80));
    }

    @Test
    void testHasAuthority() {
        URI uri = URI.create("http://user:pass@example.com:8080");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasAuthority());
    }

    @Test
    void testHasAuthorityWithParam() {
        URI uri = URI.create("http://user:pass@example.com:8080");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasAuthority("user:pass@example.com:8080"));
    }

    @Test
    void testHasFrament() {
        URI uri = URI.create("http://example.com#section1");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasFrament());
    }

    @Test
    void testHasFramentWithParam() {
        URI uri = URI.create("http://example.com#section1");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasFrament("section1"));
    }

    @Test
    void testHasPath() {
        URI uri = URI.create("http://example.com/path/to/resource");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasPath());
    }

    @Test
    void testHasPathWithParam() {
        URI uri = URI.create("http://example.com/path/to/resource");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasPath("/path/to/resource"));
    }

    @Test
    void testHasQuery() {
        URI uri = URI.create("http://example.com/path?query=1");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasQuery());
    }

    @Test
    void testHasQueryWithParam() {
        URI uri = URI.create("http://example.com/path?query=1");
        assertDoesNotThrow(() -> new CheckerURI(uri, "test").hasQuery("query=1"));
    }
}



