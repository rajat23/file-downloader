package downloader;

import console.Display;
import io.Reader;
import io.Writer;
import java.io.RandomAccessFile;
import java.util.Optional;

public class Downloader {
    private StateManager state;
    private Reader reader;
    private Writer writer;

    public Downloader(StateManager state,Reader reader, Writer writer) {
        this.state = state;
        this.reader = reader;
        this.writer = writer;
    }

    public Optional<RandomAccessFile> download() {
        while (state.isDownloading()) {
            byte [] buffer = new byte[4096];
            int bytesRead = reader.readContent(buffer);
            if (noMoreContentAvailable(bytesRead))
                break;
            state.trackContentDownloaded(bytesRead);
            Display.progress();
            writer.write(bytesRead,buffer);
        }
        return writer.getRandomAccessFile();
    }

    private boolean noMoreContentAvailable(int bytesRead) {
        return bytesRead == -1;
    }

}
