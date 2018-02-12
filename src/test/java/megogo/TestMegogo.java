package megogo;

import megogo.responseMegogoClasses.Program;
import megogo.responseProviderClasses.Programme;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(value = Parameterized.class)
public class TestMegogo {
    LocalTimeHelper localTimeHelper = null;
    ProviderHelper providerHelper = null;
    MegogoHelper megogoHelper = null;
    ArrayList<Program> programMegogoList = null;
    List<Programme> programProviderList =null;
    String urlMegogo = null;
    String urlProvider = null;
    String urlLocalTime = null;
    ZonedDateTime localTime = null;
    Logger logger = null;

    private int megogoNumber;
    private int providerNumber;
    private String channelName;

    public TestMegogo(int megogoNumber, int providerNumer, String channelName) {
        this.megogoNumber = megogoNumber;
        this.providerNumber = providerNumer;
        this.channelName = channelName;
    }

    @Parameterized.Parameters(name = "{index}: testTVChannel {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {295, 3, "1+1"},
                {298, 1202, "LTV1"},
                {300, 1159, "Riga24"},
        });
    }

        @Before
        public void startUp() throws Exception {
            logger = LoggerFactory.getLogger(TestMegogo.class);

            localTimeHelper = new LocalTimeHelper();
            providerHelper = new ProviderHelper();
            megogoHelper = new MegogoHelper();

            File file = new File("test.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            urlMegogo = properties.getProperty("megogoURL");
            urlProvider = properties.getProperty("providerURL");
            urlLocalTime = properties.getProperty("localTimeURL");

            localTimeHelper.setLocalTime(urlLocalTime);
            localTime = localTimeHelper.getLocalTime();
        }

        @Test
        public void testTVProgram() {
            logger.info("-----------------------------------");
            logger.info("channel name: {}", channelName);
            logger.info("{} is started", Thread.currentThread().getStackTrace()[1].getMethodName());
            programMegogoList = megogoHelper.getMegogoList(urlMegogo + megogoNumber);
            programProviderList = providerHelper.getProviderList(urlProvider + providerNumber + ".xml", localTime);

            logger.debug("programMegogoList size: {}", programMegogoList.size());
            logger.debug("programProviderList size: {}", programProviderList.size());

            assertNotNull("programMegogoList is Null", programMegogoList);
            logger.info("programMegogoList is not null -> ok");
            assertNotNull("programProviderList", programProviderList);
            logger.info("programProviderList is not null -> ok");
            assertEquals(programProviderList.size(), programMegogoList.size());
            logger.info("programMegogoList is equal to programProviderList -> ok");


            for (int i =0; i<  programProviderList.size(); i++)
            {
                logger.info("checking item {} ...", i);
                Programme programProvider = programProviderList.get(i);
                Program programMegogo = programMegogoList.get(i);
                assertEquals(providerHelper.getTitle(programProvider), megogoHelper.getTitle(programMegogo));
                assertEquals(providerHelper.getStartTime(programProvider), megogoHelper.getStartTime(programMegogo, localTime.getZone()));
                assertEquals(providerHelper.getEndTime(programProvider), megogoHelper.getEndTime(programMegogo, localTime.getZone()));
                assertEquals(providerHelper.getDescription(programProvider), megogoHelper.getDescription(programMegogo));
                assertEquals(providerHelper.getGenre(programProvider), megogoHelper.getGenre(programMegogo));
                assertEquals(providerHelper.getYear(programProvider), megogoHelper.getYear(programMegogo));
                logger.info("title, startTime, endTime, description, genre and year are ok");
            }
        }
}
