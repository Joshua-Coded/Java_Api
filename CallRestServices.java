import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallRestServices {
    // open connection
    public static void main(String[] args) {
        // write the code in a try and catch block
        try {
            // provide a new url

            URL url = new URL("https://regres.in/api/users");
            // initiate a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // set properties to the connection
            conn.setDoOutput(true);
            // what kind of method are we calling
            conn.setRequestMethod("POST");
            // the contents type
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("User-Agent", "Firefox");
            // create the input types
            String input = "{\"name\":\"joshua\",\"job\":\"softwareEngineer\"}";
            // open the stream
            OutputStream os = conn.getOutputStream();
            // write the data
            os.write(input.getBytes());
            // flush the data
            os.flush();

            // logic for handling errors instances
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("failed to create connection :Http Error Code :" + conn.getResponseCode());
            }

            // read the output
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            System.out.println("Response from API: ");
            while ((output = br.readLine()) != null) {
                // print the response
                System.out.println(output);
            }

            // close the connection
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}