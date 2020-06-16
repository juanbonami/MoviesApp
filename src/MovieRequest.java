import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import org.json.JSONObject;
import java.net.URL;

public class MovieRequest {

    private static HttpURLConnection connection;

    public static void main(String[] args) {

        // BufferedReader gets response when using HttpURLConnection
        BufferedReader reader;
        StringBuffer responseContent = new StringBuffer();
        String line;

        try {
            URL url = new URL("http://www.omdbapi.com/?apikey=8e2e09a4&t=spider-man");
            // opens connection
            connection = (HttpURLConnection) url.openConnection();

            // setup request
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

            // handles if connection is NOT successful
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                // ends connection
                reader.close();
            }
            // handles successful connection
            else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // ends HttpURLConnection
            connection.disconnect();
        }
    }


    public String formatMovieString(String title) {
        char[] charTitle = title.toCharArray();
        String formattedTitle = "";
        for (int i = 0; i < charTitle.length; i++) {
            if (charTitle[i] == ' ') {
                formattedTitle = formattedTitle + "+";
            } else {
                formattedTitle = formattedTitle + charTitle[i];
            }
        }
        return formattedTitle;
    }


    String title;
    String details;
    
    public void parseResponse(String responseBody) {

        JSONObject movie = new JSONObject(responseBody);
        title = movie.getString("Title");
        details = "Title: " + title;
    }
}
