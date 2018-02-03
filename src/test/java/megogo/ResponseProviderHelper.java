package megogo;

import com.jayway.restassured.response.Response;
import megogo.responseProviderClasses.Programme;
import megogo.responseProviderClasses.Tv;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.jayway.restassured.RestAssured.get;
import static megogo.ResponseLocalTimeHelper.getLocalTime;

public class ResponseProviderHelper {

    final static String FORMAT_TYPE = "y-M-d H:m:s XX";

    public static List<Programme> getProviderList(String urlProvider) {
        List<Programme> programArrayList = null;
        Response response;

        response = get(urlProvider);
        if ((response.statusCode()==200)) {
            Tv responseQuery = response.getBody().as(Tv.class);
            List<Programme> programArrayListTemp = Arrays.asList(responseQuery.getProgramme());
            ZonedDateTime localTime = getLocalTime();

            int startIndex = findProgramIndexByDate(programArrayListTemp, localTime);
            int endIndex = findProgramIndexByDate(programArrayListTemp, localTime.plusDays(1));
            programArrayList = programArrayListTemp.subList(startIndex,endIndex+1);
        }
        else {
            System.out.println("connection problem: " + response.statusCode()); }
        return programArrayList;
    }

    public static String getTitle(Programme programme) {
        return  programme.getTitle().getContent();
    }

    public static ZonedDateTime getStartTime (Programme programme) {
        String startTimeStr = programme.getStart().toString();
        return convertStrToZoneDate(startTimeStr);
    }

    public static ZonedDateTime getEndTime (Programme programme) {
        String endTimeStr = programme.getStop().toString();
        return convertStrToZoneDate(endTimeStr);
    }

    public static String getDescription(Programme programme) {
        if (programme.getDesc()==null) {return null;}
        return programme.getDesc().getContent();
    }

    public static String getGenre(Programme programme) {
        if (programme.getGenre()==null) {return null;}
        return programme.getGenre().getContent();
    }

    public static String getYear(Programme programme) {
        if (programme.getProduction_year()== null) {return null;}
        return programme.getProduction_year();
    }

    public static ZonedDateTime convertStrToZoneDate (String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE, Locale.US);
        return ZonedDateTime.parse(string, formatter);
    }

    public static int findProgramIndexByDate(List<Programme> programProviderList, ZonedDateTime zDate) {
        for (int i = 0; i < programProviderList.size(); i++) {
            ZonedDateTime programStartTime = getStartTime(programProviderList.get(i));
            ZonedDateTime programEndTime = getEndTime(programProviderList.get(i));
            if (((zDate.isEqual(programStartTime))||(zDate.isAfter(programStartTime)))&&
                    ((zDate.isEqual(programEndTime))||(zDate.isBefore(programEndTime))))
            {return i;}
        }
        return 0;
    }
}
