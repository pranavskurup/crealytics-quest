package quest.crealytics.util;

import org.junit.Assert;
import org.junit.Test;
import quest.crealytics.exception.MonthNotFoundException;

import java.time.DateTimeException;
import java.time.Month;

/**
 * Created by Pranav S Kurup on 5/13/2018.
 */
public class MonthUtilTest {
    @Test
    public void ofMonthNumericValid() throws Exception {
        for (int i = 1; i <= 12; i++) {
            Assert.assertEquals(MonthUtil.of(String.valueOf(i)), Month.of(i));
        }
    }

    @Test(expected = MonthNotFoundException.class)
    public void ofMonthNumericInvalidPositive() throws Exception {
        String mon = "13";
        MonthUtil.of(mon);
    }

    @Test(expected = MonthNotFoundException.class)
    public void ofMonthNumericInvalidNegative() throws Exception {
        String mon = "-1";
        MonthUtil.of(mon);
    }

    @Test
    public void ofMonthStringValid() throws Exception {
        Month[] months = Month.values();
        for (int i = 0; i < months.length; i++) {
            Month month = months[i];
            String monthFullName = months[i].name().toLowerCase();
            String monthShortName = monthFullName.substring(0, 3);
            Assert.assertEquals(MonthUtil.of(monthFullName), month);
            Assert.assertEquals(MonthUtil.of(monthShortName), month);
        }
    }
}