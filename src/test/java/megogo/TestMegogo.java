package megogo;

import megogo.responseMegogoClasses.Program;
import megogo.responseProviderClasses.Programme;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import static megogo.ResponseMegogoHelper.*;
import static megogo.ResponseProviderHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestMegogo {
    ArrayList<Program> programMegogoList = null;
    List<Programme> programProviderList =null;

    String urlLocalTime = "http://epg.megogo.net/time";

    @Before
    public void startUp() throws Exception {
        String urlMegogo = null;
        String urlProvider = null;

        File file = new File("test.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        Enumeration enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String key = (String) enuKeys.nextElement();
            if (key.equals("megogoURL"))  { urlMegogo = properties.getProperty(key); }
            if (key.equals("providerURL"))  { urlProvider = properties.getProperty(key); }
            if (key.equals("localTimeURL")) { urlLocalTime = properties.getProperty(key); }
        }
        programMegogoList = getMegogoList(urlMegogo);
        programProviderList = getProviderList(urlProvider, urlLocalTime);

        assertNotNull("programMegogoList is Null", programMegogoList);
        assertNotNull("programProviderList", programProviderList);
    }

    @Test
    public void testProgramEquals() throws Exception {

        for (int i =0; i<  programProviderList.size(); i++)
        {
            Programme programProvider = programProviderList.get(i);
            Program programMegogo = programMegogoList.get(i);
            // System.out.println(i);
            // System.out.println(getTitle(programProvider));
            assertEquals(getTitle(programProvider), getTitle(programMegogo));
            assertEquals(getStartTime(programProvider), getStartTime(programMegogo));
            assertEquals(getEndTime(programProvider), getEndTime(programMegogo));
            assertEquals(getDescription(programProvider), getDescription(programMegogo));
            assertEquals(getGenre(programProvider), getGenre(programMegogo));
            assertEquals(getYear(programProvider), getYear(programMegogo));
        }
    }

    @Test
    public void programVerifier()
    {
        System.out.println("---------");

        ArrayList<Programme> testProgramDel = new ArrayList<Programme>(programProviderList);
       // System.out.println(testProgramDel.get(1).getTitle().getContent());
        testProgramDel.remove(0);
        testProgramDel.remove(10);
        //testProgramDel.add(testProgramDel.get(0));
        //testProgramDel.add(testProgramDel.get(1));
        //System.out.println(testProgramDel.get(1).getTitle().getContent());

        ArrayList<Programme> testProgramAdd = new ArrayList<Programme>(programProviderList);
        //System.out.println(testProgramAdd.get(1).getTitle().getContent());
        testProgramAdd.add(1, testProgramAdd.get(6));
        testProgramAdd.add(6, testProgramAdd.get(9));
        //System.out.println(testProgramAdd.get(1).getTitle().getContent());

        int b = 0;
        for (int c = 0; c < programProviderList.size()-2; c++) {
            System.out.println("----------"+ c + "---------");
            if (testProgramDel.get(c).getTitle().getContent().equals(programMegogoList.get(b).getTitle())) {
                System.out.println("test is oK");
                b++;
            }
            else {
                if (testProgramDel.get(c).getTitle().getContent().equals(programMegogoList.get(b+1).getTitle())) {
                    System.out.println("program with index [" + c + "] was deleted from MegogoList");
                    b = b+2; //TODO boundary check
                }
                else if (programMegogoList.get(b).getTitle().equals(testProgramDel.get(c+1).getTitle().getContent())) {
                    System.out.println("new element was added between " + (c-1) + " and "+ c + " to MegogoList");
                    c = c++; //TODO boundary check
                }
                else {
                    System.out.println("program was changed");
                }
            }
        }
    }

}
