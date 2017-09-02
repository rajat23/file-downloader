package downloader;

import io.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class StateManagerUnitTest {

    private StateManager state;

    @Mock
    private Writer writer;

    @Before
    public void setUp() {
        state = new StateManager(writer);
    }

    @Test
    public void shouldBeInDownloadingStateWhenDownloadStarts() {
        assertTrue(state.isDownloading());
    }

    @Test
    public void shouldBeAbleToTrackTheContentDownloadedSoFar() {
        state.trackContentDownloaded(100);

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.TYPE);
        captor.capture();

        state.seek();
        Mockito.verify(writer).seek(captor.capture());
        assertEquals(captor.getValue(),100,0.0);
    }
}