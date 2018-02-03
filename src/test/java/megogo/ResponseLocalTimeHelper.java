package megogo;

import com.jayway.restassured.response.Response;
import megogo.responseLocalDateClasses.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.jayway.restassured.RestAssured.get;

public class ResponseLocalTimeHelper {
    private static ZoneId zoneId = null;
    private static ZonedDateTime localTime = null;
    final static String FORMAT_TYPE = "EEEE, MMMM d, y h:mm:ss a XXX";

    public static ZoneId getZoneId() {
        return zoneId;
    }

    public static ZonedDateTime getLocalTime() {
        return localTime;
    }

    public static void setLocalTime(String urlStr) {
        Response response = get(urlStr);

        if (response.statusCode()==200){
            LocalTime responseQuery = response.getBody().as(LocalTime.class);
            localTime = convertStrToZoneDate(responseQuery.getData().getTimeLocal());
            zoneId = localTime.getZone();
            }
        else {
            System.out.println("connection problem: " + response.statusCode());
        }
    }

    public static ZonedDateTime convertStrToZoneDate (String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE, Locale.US);
        return ZonedDateTime.parse(string, formatter);
    }
}
