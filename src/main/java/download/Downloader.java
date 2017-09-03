package download;

import console.Display;
import io.Reader;
import io.Writer;
import lombok.AllArgsConstructor;

import java.io.RandomAccessFile;
import java.util.Optional;

@AllArgsConstructor
public class Downloader {
    private StateManager state;
    private Reader reader;
    private Writer writer;
    private static final int BUFFER_SIZE = 4096;

    public Optional<RandomAccessFile> download() {
        while (state.isDownloading()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = reader.readContent(buffer);
            if (noMoreContentAvailable(bytesRead))
                break;
            state.trackContentDownloaded(bytesRead);
            Display.progress();
            writer.write(bytesRead, buffer);
        }
        return writer.getRandomAccessFile();
    }

    private boolean noMoreContentAvailable(int bytesRead) {
        return bytesRead == -1;
    }

}
