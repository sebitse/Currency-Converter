package currencyConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class CurrencyConverterAPI {
    /**
     * Return current exchange rate.
     *
     * @param apiKey is the API key to access the conversion service.
     * @param baseCurrency is the base currency for which exchange rates are desired.
     * @return A JSON object containing the exchange rates or null in case of error.
     */
    @SuppressWarnings("resource")
    public static JSONObject fetchExchangeRates(String apiKey, String baseCurrency) {
        try {
            // Build the URL to access the conversion API
            URL url = new URL("https://open.er-api.com/v6/latest/" + baseCurrency + "?apikey=" + apiKey);

            // Open the connection to the URL to retrieve data
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the input stream to read the answer
            InputStream inputStream = connection.getInputStream();

            // Read the response from API
            String response = new Scanner(inputStream).useDelimiter("\\A").next();

            // Parsing the JSON response and returning the JSON object containing the exchange rates
            return new JSONObject(response).getJSONObject("rates");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
