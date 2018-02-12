package megogo;

import com.jayway.restassured.response.Response;
import megogo.responseLocalDateClasses.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.jayway.restassured.RestAssured.get;

public class LocalTimeHelper {
    private ZonedDateTime localTime = null;
    private final String FORMAT_TYPE = "EEEE, MMMM d, y h:mm:ss a XXX";

    public ZonedDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(String urlStr) {
        Response response = get(urlStr);

        if (response.statusCode()==200){
            LocalTime responseQuery = response.getBody().as(LocalTime.class);
            localTime = convertStrToZoneDate(responseQuery.getData().getTimeLocal());
            }
        else {
            System.out.println("connection problem: " + response.statusCode());
        }
    }

    public ZonedDateTime convertStrToZoneDate (String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE, Locale.US);
        return ZonedDateTime.parse(string, formatter);
    }
}
