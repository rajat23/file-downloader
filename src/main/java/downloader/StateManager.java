package downloader;

import io.Writer;

public class StateManager {

    private enum Status {
        DOWNLOADING
    }

    private Status status;
    private int downloaded;
    private Writer writer;

    public StateManager(Writer writer) {
        this.downloaded = 0;
        this.writer = writer;
        this.status = Status.DOWNLOADING;
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

}
