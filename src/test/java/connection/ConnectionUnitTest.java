package connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionUnitTest {

    private Connection connection;

    @Before
    public void setUp() {
        connection = new Connection();
    }

    @Test
    public void shouldCreateHttpUrlConnectionForGivenURL() throws IOException {
        String url = "http://sample.com/file";

        connection.connectTo(url);

        assertEquals(new URL("http://sample.com/file"), connection.getHttpURLConnection().getURL());

    }

}