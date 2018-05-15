package quest.crealytics.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import quest.crealytics.exception.MonthNotFoundException;

import java.time.DateTimeException;
import java.time.Month;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Slf4j
public final class MonthUtil {
    private MonthUtil() {

    }

    private static Month of(final int month) {
        return Month.of(month);
    }

    public static Month of(final String mon) {
        if (mon == null) {
            throw new MonthNotFoundException("Invalid value for MonthOfYear");
        }
        Month currentMonth = null;
        try {
            if (NumberUtils.isDigits(mon)) {
                currentMonth = of(Integer.parseInt(mon));
            } else {
                currentMonth = of(mon, Month.values());
            }
        } catch (DateTimeException e) {
            throw new MonthNotFoundException(e.getMessage());
        }
        if (currentMonth == null) {
            throw new MonthNotFoundException("Invalid value for MonthOfYear: " + mon);
        }
        log.debug("Converted {} to [] ", mon, currentMonth);
        return currentMonth;
    }

    private static Month of(String mon, Month[] months) {
        Month currentMonth = null;
        for (int i = 0; i < months.length; i++) {
            Month month = months[i];
            String name = months[i].name();
            if (name.equalsIgnoreCase(mon) || (mon.length() == 3 && name.startsWith(mon.toUpperCase()))) {
                currentMonth = month;
                break;
            }
        }
        return currentMonth;
    }
}
