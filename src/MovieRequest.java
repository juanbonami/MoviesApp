import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import org.json.JSONObject;
import java.net.URL;

public class MovieRequest {

    private static HttpURLConnection connection;

    public void executeAPI(String movieString) {

        // BufferedReader gets response when using HttpURLConnection
        BufferedReader reader;
        StringBuffer responseContent = new StringBuffer();
        String line;
        String urlString = "http://www.omdbapi.com/?apikey=8e2e09a4&t=";
        urlString = urlString + formatMovieString(movieString);

        try {
            URL url = new URL(urlString);
            // opens connection
            connection = (HttpURLConnection) url.openConnection();

            // setup request
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

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
                parseResponse(responseContent.toString());
            }
            System.out.println(responseContent.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            // ends HttpURLConnection
            connection.disconnect();
        }
    }


    private String formatMovieString(String title) {
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


    private String title, plot, year, rated, released, runtime, genre, director, writer, actors, language, country,
    awards, poster;
    String details;
    
    private void parseResponse(String responseBody) {

        JSONObject movie = new JSONObject(responseBody);
        title = movie.getString("Title");
        plot = movie.getString("Plot");
        year = movie.getString("Year");
        rated = movie.getString("Rated");
        released = movie.getString("Released");
        runtime = movie.getString("Runtime");
        genre = movie.getString("Genre");
        director = movie.getString("Director");
        writer = movie.getString("Writer");
        actors = movie.getString("Actors");
        language = movie.getString("Language");
        country = movie.getString("Country");
        awards = movie.getString("Awards");
        poster = movie.getString("Poster");

        details = "Title: " + title + "\nPlot: " + plot
        + "\nYear: " + year + "\nRated: " + rated
        + "\nReleased: " + released + "\nRuntime: " + runtime
        + "\nGenre: " + genre + "\nDirector: " + director
        + "\nWriter: " + writer + "\nActors: " + actors
        + "\nLanguage: " + language + "\nCountry: " + country
        + "\nAwards: " + awards + "\nPoster: " + poster;
    }
}
