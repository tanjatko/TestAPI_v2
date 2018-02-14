package megogo;

import io.restassured.response.Response;
import megogo.responseMegogoClasses.Program;
import megogo.responseMegogoClasses.ResponseQuery;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static io.restassured.RestAssured.get;

public class MegogoHelper {

    private final String FORMAT_TYPE = "MMM d, y h:mm:ss a";
    private Response response;

    public void setResponse(String urlStr) {
        response = get(urlStr);
    }
    public Response getResponse() {
        return response;
    }

    public ArrayList<Program> getMegogoList(Response response) {
        ArrayList<Program> programArrayList = null;
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