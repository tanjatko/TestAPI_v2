package megogo;

import com.jayway.restassured.response.Response;
import megogo.responseMegogoClasses.Program;
import megogo.responseMegogoClasses.ResponseQuery;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import static com.jayway.restassured.RestAssured.get;
import static megogo.ResponseLocalTimeHelper.getZoneId;

public class ResponseMegogoHelper {

    final static String FORMAT_TYPE = "MMM d, y h:mm:ss a";

    public static ArrayList<Program> getMegogoList(String urlStr) {
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

    public static String getTitle(Program program) {
        return program.getTitle();
    }

    public static ZonedDateTime getStartTime(Program program) {
        String startTimeStr = program.getStart();
        return convertStrToZoneDate(startTimeStr);
    }

    public static ZonedDateTime getEndTime(Program program) {
        String endTimeStr = program.getEnd();
        return convertStrToZoneDate(endTimeStr);
    }

    public static String getDescription(Program program) {
        return program.getDescription();
    }

    public static String getGenre(Program program) {
        if (program.getGenre().getTitle().equals("Другое")) {return null;}
        return program.getGenre().getTitle();
    }

    public static String getYear(Program program){
        return program.getYear();
    }

    public static ZonedDateTime convertStrToZoneDate (String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE, Locale.US);
        return ZonedDateTime.parse(string, formatter.withZone(getZoneId()));
    }
}