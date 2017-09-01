package connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionTest {

    @Test
    public void shouldCreateHttpUrlConnectionForGivenURL() throws IOException {
        String url = "http://sample.com/file";

        Optional<URLConnection> connection = Connection.connectTo(url);

        assertEquals(new URL("http://sample.com/file"),connection.get().getURL());

    }

}