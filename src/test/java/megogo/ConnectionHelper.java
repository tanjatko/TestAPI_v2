package megogo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionHelper {

    public static HttpURLConnection startConnection(HttpURLConnection connection, String url) throws IOException {
        connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(1000);
        connection.connect();
        return connection;
    }
}
