package quest.crealytics.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.time.Year;
import java.util.stream.Stream;

public class ReportEntityTest {

    @Test
    public void remapWithoutMonthAndReportSite() {
        remapWithMonthAndReportSite(null,null);
    }


    @Test
    public void remapWithMonthAndReportSite() {
        Stream.of(Month.values()).forEach(month -> {
            Stream.of(ReportSite.values()).forEach(site -> {
                remapWithMonthAndReportSite(month,site);
            });
        });
    }

    private void remapWithMonthAndReportSite(Month  month, ReportSite site) {

        ReportEntity.ReportEntityBuilder builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("android")).month(Month.APRIL).year(Year.of(2018)).build())
                .requests(new BigInteger("9914106"))
                .impressions(new BigInteger("9412958"))
                .clicks(new BigInteger("24395"))
                .conversions(new BigInteger("6018"))
                .revenue(new BigDecimal("18110.41"));
        ReportEntity ent1 = builder.build();
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("iOS")).month(Month.APRIL).year(Year.of(2018)).build())
                .requests(new BigInteger("2550165"))
                .impressions(new BigInteger("2419733"))
                .clicks(new BigInteger("6331"))
                .conversions(new BigInteger("1564"))
                .revenue(new BigDecimal("4692.28"));
        ReportEntity ent2 = builder.build();


        ReportEntity.ReportEntityBuilder expectedEntitybuilder = ReportEntity.builder();
        ReportID.ReportIDBuilder expectedEntityIdBuilder = ReportID.builder();
        expectedEntityIdBuilder.month(month).site(site);
        ReportID expectedID = expectedEntityIdBuilder.build();
        ReportEntity expectedEntity = expectedEntitybuilder.id(expectedID)
                .requests(ent1.getRequests().add(ent2.getRequests())).
                        impressions(ent1.getImpressions().add(ent2.getImpressions())).
                        clicks(ent1.getClicks().add(ent2.getClicks())).
                        conversions(ent1.getConversions().add(ent2.getConversions())).
                        revenue(ent1.getRevenue().add(ent2.getRevenue())).build();

        Assert.assertTrue(ReportEntity.remap(month, site).apply(ent1, ent2).equals(expectedEntity));
    }
}