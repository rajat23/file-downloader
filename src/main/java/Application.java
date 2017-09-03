import connection.Connection;
import download.Downloader;
import download.DownloaderManager;
import download.StateManager;
import io.Reader;
import io.Writer;

public class Application {

    public static void main(String[] args) {

        Reader reader = new Reader();
        Writer writer = new Writer();
        Connection connection = new Connection();
        StateManager stateManager = new StateManager(writer,connection);
        Downloader downloader = new Downloader(stateManager,reader,writer);

        DownloaderManager downloaderManager = new DownloaderManager(connection, reader, writer,downloader);
        downloaderManager.download("https://www.nginx.com/wp-content/uploads/2015/01/Building_Microservices_Nginx.pdf", "/Users/rajatc/Desktop");
    }
}
