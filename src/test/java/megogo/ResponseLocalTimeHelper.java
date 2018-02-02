package megogo;

import com.google.gson.Gson;
import megogo.responseLocalDateClasses.LocalTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ResponseLocalTimeHelper {
    static HttpURLConnection connection = null;
    static LocalTime responseQuery = null;
    static String getQuery = null;
    static ZoneId zoneId = null;
    final static String FORMAT_TYPE = "EEEE, MMMM d, y h:mm:ss a XXX";

    public static ZonedDateTime getLocalTime(String urlStr) throws IOException, ParseException {
        getQuery = urlStr;
        connection = ConnectionHelper.startConnection(connection, getQuery);

        StringBuffer responseString = new StringBuffer();

        if (HttpURLConnection.HTTP_OK == connection.getResponseCode())
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine())!= null) {
                responseString.append(line);
                responseString.append("\n");
            }
            Gson gson = new Gson();
            responseQuery = gson.fromJson(responseString.toString(), LocalTime.class);
        }
        else {
            System.out.println("fail:" + connection.getResponseCode()+"");
        }
        return convertStrToZoneDate(responseQuery.getData().getTimeLocal());
    }

    public static ZonedDateTime convertStrToZoneDate (String string)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE);
        ZonedDateTime zd =   ZonedDateTime.parse(string, formatter);
        zoneId = zd.getZone();
        return zd;
    }
}
