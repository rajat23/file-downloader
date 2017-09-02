package download;

import connection.Connection;
import console.Display;
import io.Reader;
import io.Writer;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.io.RandomAccessFile;
import java.util.Optional;

@AllArgsConstructor
public class DownloaderManager {
    private Connection connection;
    private Reader reader;
    private Writer writer;
    private Downloader downloader;

    public Optional<RandomAccessFile> download(@NonNull String url, @NonNull String location) {
        connection.connectTo(url);
        reader.obtainInputStream(connection);
        writer.obtainOutputStream(location, url);

        Display.startProgress();
        Optional<RandomAccessFile> file = downloader.download();
        Display.endProgress();

        close();
        return file;
    }

    private void close() {
        reader.close();
        writer.close();
        connection.disconnect();
    }

}
