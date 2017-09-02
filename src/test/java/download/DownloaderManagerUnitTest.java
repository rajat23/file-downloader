package download;

import connection.Connection;
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
public class DownloaderManagerUnitTest {

    private DownloaderManager downloaderManager;

    @Mock
    private Downloader downloader;

    @Mock
    private Connection connection;

    @Mock
    private Reader reader;

    @Mock
    private Writer writer;


    @Before
    public void setUp() {
        downloaderManager = new DownloaderManager(connection, reader, writer,downloader);
    }

    @Test
    public void shouldBeAbleToCompleteDownloadPrerequisiteAndThenDownloadFile() throws IOException {

        String url = "http://sample.com/file";
        String location = "/desktop/folder";
        Optional<RandomAccessFile> fileToBeDownload = Optional.of(new RandomAccessFile("src/test/resources/sample.txt", "rw"));
        int fileSize = 3278;

        doNothing().when(connection).connectTo(url);
        doNothing().when(reader).obtainInputStream(connection);
        doNothing().when(writer).obtainOutputStream(location, url);

        when(downloader.download()).thenReturn(fileToBeDownload);
        Optional<RandomAccessFile> actualFile = downloaderManager.download(url,location);

        assertEquals(actualFile.get().length(), fileSize);

        verify(connection).connectTo(url);
        verify(reader).obtainInputStream(connection);
        verify(writer).obtainOutputStream(location, url);
    }

}