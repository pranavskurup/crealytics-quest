package quest.crealytics.model;

import org.springframework.util.Assert;
import quest.crealytics.exception.ReportHeaderNotFoundException;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
public enum ReportHeader {

    SITE("site"), REQUESTS("requests"), IMPRESSIONS("impressions"), CLCIKS("clicks"), CONVERSIONS("conversions"), REVENUE("revenue (USD)");
    private String header;

    ReportHeader(String header) {
        this.header = header;
    }

    public static ReportHeader ofHeader(final String header) {
        String trimmed=header.trim();
        Assert.notNull(header, "Header is mandatory");
        ReportHeader[] headers = ReportHeader.values();
        for (int i = 0; i < headers.length; i++) {
            ReportHeader reportHeader = headers[i];
            if (reportHeader.getHeader().equals(trimmed)) {
                return reportHeader;
            }
        }
        throw new ReportHeaderNotFoundException("No valid report header found for: " + header);
    }

    public String getHeader() {
        return header;
    }

}
