package connection;

import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    @Getter
    private HttpURLConnection httpURLConnection;

    public void connectTo(@NonNull String downloadUrl) {
        try {
            URL url = new URL(downloadUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
        } catch (IOException e) {
            System.out.println("Could not connect to given URL.Please make sure you are connected to internet");
            System.exit(0);
        }
    }

    public void disconnect() {
        httpURLConnection.disconnect();
    }
}
