package megogo;

import com.jayway.restassured.response.Response;
import megogo.responseMegogoClasses.Program;
import megogo.responseMegogoClasses.ResponseQuery;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static com.jayway.restassured.RestAssured.get;

public class MegogoHelper {

    private final String FORMAT_TYPE = "MMM d, y h:mm:ss a";

    public ArrayList<Program> getMegogoList(String urlStr) {
        ArrayList<Program> programArrayList = null;
        Response response;

        response = get(urlStr);
        if ((response.statusCode()==200)) {
            ResponseQuery responseQuery = response.getBody().as(ResponseQuery.class);
            programArrayList = (ArrayList<Program>) responseQuery.getData().get(0).getPrograms();
        }
        else {
            System.out.println("connection problem: " + response.statusCode());
        }
        return programArrayList ;
    }

    public String getTitle(Program program) {
            return program.getTitle();
    }

    public ZonedDateTime getStartTime(Program program, ZoneId zoneId) {
        String startTimeStr = program.getStart();
        return convertStrToZoneDate(startTimeStr, zoneId);
    }

    public ZonedDateTime getEndTime(Program program, ZoneId zoneId) {
        String endTimeStr = program.getEnd();
        return convertStrToZoneDate(endTimeStr, zoneId);
    }

    public String getDescription(Program program) {
        return program.getDescription();
    }

    public String getGenre(Program program) {
        if (program.getGenre().getTitle().equals("Другое")) {return null;}
        return program.getGenre().getTitle();
    }

    public String getYear(Program program){
        return program.getYear();
    }

    public ZonedDateTime convertStrToZoneDate (String string, ZoneId zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE, Locale.US);
        return ZonedDateTime.parse(string, formatter.withZone(zoneId));
    }
}