package io;

import connection.Connection;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class Reader {

    @Getter
    private Optional<InputStream> inputStream;

    public Reader() {
        inputStream = Optional.empty();
    }

    public void obtainInputStream(Connection connection) {
        try {
            inputStream = Optional.of(connection.getHttpURLConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readContent(byte[] buffer) {
        int bytesRead = 0;
        try {
            if (inputStream.isPresent()) {
                bytesRead = inputStream.get().read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytesRead;
    }

    public void close() {
        inputStream.ifPresent(stream -> {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
