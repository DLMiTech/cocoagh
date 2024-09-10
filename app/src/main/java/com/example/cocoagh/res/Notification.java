package com.example.cocoagh.res;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Notification {
    public static boolean sendSMS(String to, String message) {
        String apiUrl = "https://sms.smsnotifygh.com/smsapi";
        String apiKey = "d5efbd45-cdea-4e39-9d25-04990ac77396";
        String senderId = "Vote KTU";

        try {
            // Create the JSON object to send in the request
            JSONObject jsonData = new JSONObject();
            jsonData.put("key", apiKey);
            jsonData.put("to", to);
            jsonData.put("msg", message);
            jsonData.put("sender_id", senderId);

            // Create the URL and open the connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Write the JSON data to the output stream
            OutputStream os = conn.getOutputStream();
            os.write(jsonData.toString().getBytes("UTF-8"));
            os.close();

            // Get the response code
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return false; // Request failed
            }

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the response (assuming JSON)
            JSONObject responseObject = new JSONObject(response.toString());
            if (responseObject.has("error")) {
                return false; // API request failed
            }

            return true; // API request successful

        } catch (Exception e) {
            e.printStackTrace();
            return false; // Exception occurred
        }
    }
}
