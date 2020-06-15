import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieRequest {

    private static HttpURLConnection connection;

    public static void main(String[] args) {

        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=8e2e09a4&t=");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
