package connection;

import lombok.Getter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    @Getter
    private HttpURLConnection httpURLConnection;

    public void connectTo(String downloadUrl) {
        try {
            URL url = new URL(downloadUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
        } catch (IOException e) {}
    }

    public void disconnect() {
        httpURLConnection.disconnect();
    }
}
