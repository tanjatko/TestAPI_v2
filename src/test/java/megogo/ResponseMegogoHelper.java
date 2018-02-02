package megogo;

import com.google.gson.Gson;
import megogo.responseMegogoClasses.Program;
import megogo.responseMegogoClasses.ResponseQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ResponseMegogoHelper {
    static HttpURLConnection connection = null;
    static ResponseQuery responseQuery = null;
    static String getQuery = null;
    final static String FORMAT_TYPE = "MMM d, y h:mm:ss a";

    public static ArrayList<Program> getMegogoList(String urlStr) throws IOException {
        getQuery = urlStr;
        connection = ConnectionHelper.startConnection(connection, getQuery);

        StringBuffer responseString = new StringBuffer();
        ArrayList<Program> programMegogoList = new ArrayList<Program>();

        if (HttpURLConnection.HTTP_OK == connection.getResponseCode())
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine())!= null) {
                responseString.append(line);
                responseString.append("\n");
            }
            Gson gson = new Gson();
            responseQuery = gson.fromJson(responseString.toString(), ResponseQuery.class);
            programMegogoList = (ArrayList<Program>) responseQuery.getData().get(0).getPrograms();
            System.out.println("items to check: " +programMegogoList.size());
        }

        else {
            System.out.println("fail:" + connection.getResponseCode()+"");
        }
        return programMegogoList ;
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

    public static ZonedDateTime convertStrToZoneDate (String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE);
        ZonedDateTime zd =   ZonedDateTime.parse(string, formatter.withZone(ResponseLocalTimeHelper.zoneId));
        return zd;
    }

    public static String getDescription(Program program) {
        return program.getDescription();
    }

    public static String getGenre(Program program) {
        if (program.getGenre().getTitle().equals("Другое"))
        {return null;}
        return program.getGenre().getTitle();
    }
    public static String getYear(Program program){
        return program.getYear();
    }

}