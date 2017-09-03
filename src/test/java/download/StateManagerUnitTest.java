package download;

import connection.Connection;
import io.Writer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StateManagerUnitTest {

    private StateManager state;

    @Mock
    private Writer writer;

    @Mock
    private Connection connection;

    @Before
    public void setUp() {
        state = new StateManager(writer, connection);
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
        assertEquals(captor.getValue(), 100, 0.0);
    }

    @Test
    public void shouldBeAbleToPauseTheDownloading() {
        doNothing().when(connection).setRangeHeader(anyInt());
        doNothing().when(writer)
                .seek(anyInt());
        state.pause();

        verify(connection).setRangeHeader(anyInt());
        verify(writer).seek(anyInt());
        assertFalse(state.isDownloading());
    }

    @Test
    public void shouldBeAbleToResumeDownloading() {
        doNothing().when(connection).setRangeHeader(anyInt());
        doNothing().when(writer)
                .seek(anyInt());
        state.pause();
        verify(connection).setRangeHeader(anyInt());
        verify(writer).seek(anyInt());

        state.resume();

        assertTrue(state.isDownloading());
    }
}