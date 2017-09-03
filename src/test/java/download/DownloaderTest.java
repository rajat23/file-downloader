package download;

import io.Reader;
import io.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DownloaderTest {

    private Downloader downloader;

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;

    @Mock
    private StateManager stateManager;

    @Before
    public void setUp() {
        downloader = new Downloader(stateManager, reader, writer);
    }

    @Test
    public void shouldBeAbleToDownloadTheFile() throws IOException {
        Optional<RandomAccessFile> fileToBeDownload = Optional.of(new RandomAccessFile("src/test/resources/sample.txt", "rw"));
        byte[] buffer = new byte[4096];
        int bytesRead = 2000;
        int endBytesRead = -1;
        int fileSize = 3278;

        when(stateManager.isDownloading()).thenReturn(true);
        when(reader.readContent(buffer)).thenReturn(bytesRead).thenReturn(endBytesRead);

        doNothing().when(stateManager).trackContentDownloaded(bytesRead);
        doNothing().when(writer).write(bytesRead, buffer);
        when(writer.getRandomAccessFile()).thenReturn(fileToBeDownload);

        Optional<RandomAccessFile> actualFile = downloader.download();
        verify(stateManager, times(2)).isDownloading();
        verify(reader, times(2)).readContent(buffer);
        verify(stateManager, times(1)).trackContentDownloaded(bytesRead);
        verify(writer, times(1)).write(bytesRead, buffer);

        assertEquals(actualFile.get().length(), fileSize);
    }

}