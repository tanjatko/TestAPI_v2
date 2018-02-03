package megogo;

import megogo.responseMegogoClasses.Program;
import megogo.responseProviderClasses.Programme;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import static megogo.ResponseLocalTimeHelper.setLocalTime;
import static megogo.ResponseMegogoHelper.*;
import static megogo.ResponseProviderHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(value = Parameterized.class)
public class TestMegogo {
    ArrayList<Program> programMegogoList = null;
    List<Programme> programProviderList =null;
    String urlMegogo = null;
    String urlProvider = null;
    String urlLocalTime = null;

    private int megogoNumber;
    private int providerNumber;

    public TestMegogo(int megogoNumber, int providerNumer, String channelName) {
        this.megogoNumber = megogoNumber;
        this.providerNumber = providerNumer;
    }

    @Parameterized.Parameters(name = "{index}: testChannel {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {295, 3, "1+1"},
                {298, 1202, "LTV1"},
                {300, 1159, "Riga24"},
        });}

    @Before
    public void startUp() throws Exception {
        File file = new File("test.properties");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileInput);
        fileInput.close();

        urlMegogo = properties.getProperty("megogoURL");
        urlProvider = properties.getProperty("providerURL");
        urlLocalTime = properties.getProperty("localTimeURL");

        setLocalTime(urlLocalTime);
    }

    @Test
    public void testChannelProgram() {
        programMegogoList = getMegogoList(urlMegogo + megogoNumber);
        programProviderList = getProviderList(urlProvider + providerNumber + ".xml");

        assertNotNull("programMegogoList is Null", programMegogoList);
        assertNotNull("programProviderList", programProviderList);

        for (int i =0; i<  programProviderList.size(); i++)
        {
            Programme programProvider = programProviderList.get(i);
            Program programMegogo = programMegogoList.get(i);
            assertEquals(getTitle(programProvider), getTitle(programMegogo));
            assertEquals(getStartTime(programProvider), getStartTime(programMegogo));
            assertEquals(getEndTime(programProvider), getEndTime(programMegogo));
            assertEquals(getDescription(programProvider), getDescription(programMegogo));
            assertEquals(getGenre(programProvider), getGenre(programMegogo));
            assertEquals(getYear(programProvider), getYear(programMegogo));
        }
    }
}
