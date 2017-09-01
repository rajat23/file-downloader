package connection;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

public class Connection {

    public static Optional<URLConnection> connectTo(String downloadUrl) throws IOException {
        try {
            URL url = new URL(downloadUrl);
            return Optional.of(url.openConnection());
        } catch (IOException e) {
            return Optional.empty();
        }

    }
}
