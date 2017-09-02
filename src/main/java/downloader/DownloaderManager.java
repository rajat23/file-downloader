package downloader;

import connection.Connection;
import console.Display;
import io.Reader;
import io.Writer;
import java.io.RandomAccessFile;
import java.util.Optional;

public class DownloaderManager {
    private Connection connection;
    private Reader reader;
    private Writer writer;
    private Downloader downloader;

    public DownloaderManager(Downloader downloader, Connection connection, Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
        this.connection = connection;
        this.downloader = downloader;
    }

    public Optional<RandomAccessFile> download(String url, String location) {
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
