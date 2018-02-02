package megogo;

import megogo.responseMegogoClasses.Program;
import megogo.responseProviderClasses.Programme;
import megogo.responseProviderClasses.Tv;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static megogo.ConnectionHelper.startConnection;

public class ResponseProviderHelper {
    static HttpURLConnection connection = null;
    static String getQuery = null;
    final static String FORMAT_TYPE = "y-M-d H:m:s XX";

    public static List<Programme> getProviderList(String urlProveder, String urlLocalTime) throws Exception {
        getQuery = urlProveder;
        URL url = new URL(getQuery);
        List<Programme> programProviderListTemp = null;

        connection= startConnection(connection, getQuery);

        if (HttpURLConnection.HTTP_OK == connection.getResponseCode())
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tv.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Tv tv = (Tv) jaxbUnmarshaller.unmarshal(url);

            programProviderListTemp = Arrays.asList(tv.getProgramme());
        }
        else {
            System.out.println("fail:" + connection.getResponseCode()+"");
        }
        ZonedDateTime localTime = ResponseLocalTimeHelper.getLocalTime(urlLocalTime);

        int startIndex = findProgramIndexByDate(programProviderListTemp, localTime);
        int endIndex = findProgramIndexByDate(programProviderListTemp, localTime.plusDays(1));
        List<Programme> programProviderList =programProviderListTemp.subList(startIndex,endIndex+1);

        return programProviderList;
    }

    public static String getTitle(Programme programme)
    {
        return  programme.getTitle().getContent();
    }

    public static int findProgramIndexByDate(List<Programme> programProviderList, ZonedDateTime zDate) throws ParseException {
        for (int i = 0; i < programProviderList.size(); i++) {
            ZonedDateTime programStartTime = getStartTime(programProviderList.get(i));
            ZonedDateTime programEndTime = getEndTime(programProviderList.get(i));
            if (((zDate.isEqual(programStartTime))||(zDate.isAfter(programStartTime)))&&
                ((zDate.isEqual(programEndTime))||(zDate.isBefore(programEndTime))))
            {
                return i;
            }
        }
        return 0;
    }

    public static ZonedDateTime getStartTime (Programme programme) throws ParseException {
        String startTimeStr = programme.getStart().toString();
        return convertStrToZoneDate(startTimeStr);
    }

    public static ZonedDateTime getEndTime (Programme programme) throws ParseException {
        String endTimeStr = programme.getStop().toString();
        return convertStrToZoneDate(endTimeStr);
    }

    public static ZonedDateTime convertStrToZoneDate (String string)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_TYPE);
        ZonedDateTime zd =   ZonedDateTime.parse(string, formatter.withZone(ResponseLocalTimeHelper.zoneId));
        return zd;
    }
    public static String getDescription(Programme programme)
    {
        if (programme.getDesc()==null)
        {return null;}

        return programme.getDesc().getContent();
    }

    public static String getGenre(Programme programme)
    {
        if (programme.getGenre()==null){return null;}
        return programme.getGenre().getContent();
    }

    public static String getYear(Programme programme)
    {
        if (programme.getProduction_year()== null) { return null;}
        return programme.getProduction_year();
    }



}
