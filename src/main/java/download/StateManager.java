package download;

import connection.Connection;
import io.Writer;

public class StateManager {

    private enum Status {
        DOWNLOADING,
        PAUSE,
    }

    private Status status;
    private int downloaded;
    private Writer writer;
    private Connection connection;

    public StateManager(Writer writer, Connection connection) {
        this.downloaded = 0;
        this.writer = writer;
        this.status = Status.DOWNLOADING;
        this.connection = connection;
    }

    public boolean isDownloading() {
        return status == Status.DOWNLOADING;
    }

    public void trackContentDownloaded(int bytesRead) {
        downloaded += bytesRead;
    }

    public void seek() {
        writer.seek(downloaded);
    }

    public void pause() {
        status = Status.PAUSE;
        connection.setRangeHeader(downloaded);
        writer.seek(downloaded);
    }

    public void resume() {
        status = Status.DOWNLOADING;
    }
}
