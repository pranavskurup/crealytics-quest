package quest.crealytics.model;

import org.junit.Test;
import quest.crealytics.exception.SiteNotFoundException;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class SiteTest {

    @Test
    public void ofSiteValid() {
        assertTrue(ReportSite.ofSite("android") == ReportSite.ANDROID);
        assertTrue(ReportSite.ofSite("desktop web") == ReportSite.DESKTOP_WEB);
        assertTrue(ReportSite.ofSite("mobile web") == ReportSite.MOBILE_WEB);
        assertTrue(ReportSite.ofSite("iOS") == ReportSite.IOS);
    }

    @Test(expected = SiteNotFoundException.class)
    public void ofSiteInValid() throws Exception {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        ReportSite.ofSite(generatedString);
    }
}