package quest.crealytics.model;

import org.junit.Assert;
import org.junit.Test;
import quest.crealytics.exception.ReportHeaderNotFoundException;
import quest.crealytics.exception.SiteNotFoundException;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.Assert.*;

public class ReportHeaderTest {

    @Test
    public void ofHeader() {
        Assert.assertTrue(ReportHeader.ofHeader("site")==ReportHeader.SITE);
        Assert.assertTrue(ReportHeader.ofHeader("conversions")==ReportHeader.CONVERSIONS);
        Assert.assertTrue(ReportHeader.ofHeader("impressions")==ReportHeader.IMPRESSIONS);
        Assert.assertTrue(ReportHeader.ofHeader("revenue (USD)")==ReportHeader.REVENUE);
        Assert.assertTrue(ReportHeader.ofHeader("clicks")==ReportHeader.CLCIKS);
    }


    @Test(expected = ReportHeaderNotFoundException.class)
    public void ofSiteInValid() throws Exception{
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        ReportHeader.ofHeader(generatedString);
    }
}