import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APIFacade {
    // wow! only having a single method makes this code looks awful!
    public String getAttributeValueFromJson(String urlString, String attributeName) throws IllegalArgumentException, IOException {
        String res;
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                res = content.toString();
            } finally {
                con.disconnect();
            }
        } catch (IOException e) {
            throw new IOException("Error while reading from URL: " + e.getMessage());
        }

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(res);
            res = (String) jsonObject.get(attributeName);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while parsing JSON: " + e.getMessage());
        }

        return res;
    }
}
