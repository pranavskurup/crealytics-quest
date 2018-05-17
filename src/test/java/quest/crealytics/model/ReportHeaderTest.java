package quest.crealytics.model;

import org.junit.Test;
import quest.crealytics.exception.ReportHeaderNotFoundException;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class ReportHeaderTest {

    @Test
    public void ofHeader() {
        assertTrue(ReportHeader.ofHeader("site") == ReportHeader.SITE);
        assertTrue(ReportHeader.ofHeader("conversions") == ReportHeader.CONVERSIONS);
        assertTrue(ReportHeader.ofHeader("impressions") == ReportHeader.IMPRESSIONS);
        assertTrue(ReportHeader.ofHeader("revenue (USD)") == ReportHeader.REVENUE);
        assertTrue(ReportHeader.ofHeader("clicks") == ReportHeader.CLCIKS);
    }


    @Test(expected = ReportHeaderNotFoundException.class)
    public void ofSiteInValid() throws Exception {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        ReportHeader.ofHeader(generatedString);
    }
}