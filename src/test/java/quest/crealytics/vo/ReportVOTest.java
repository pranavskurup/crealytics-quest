package quest.crealytics.vo;

import org.junit.Test;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportID;
import quest.crealytics.model.ReportSite;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.time.Year;

import static org.junit.Assert.assertTrue;

public class ReportVOTest {

    @Test
    public void createFromEntity() {
        ReportEntity.ReportEntityBuilder builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("desktop web")).month(Month.JANUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("12483775"))
                .impressions(new BigInteger("11866157"))
                .clicks(new BigInteger("30965"))
                .conversions(new BigInteger("7608"))
                .revenue(new BigDecimal("23555.46"));
        ReportVO reportVO = ReportVO.createFromEntity(builder.build());

        ReportVO.ReportVOBuilder expectedVoBuilder = ReportVO.builder();
        ReportVO expectedVo = expectedVoBuilder.site("desktop web")
                .month("January")
                .year("2018")
                .requests(new BigInteger("12483775"))
                .impressions(new BigInteger("11866157"))
                .clicks(new BigInteger("30965"))
                .conversions(new BigInteger("7608"))
                .revenue(new BigDecimal("23555.46"))
                .ctr(0.26)
                .cr(0.06)
                .fillRate(95.05)
                .ecpm(1.99).build();
        assertTrue(expectedVo.equals(reportVO));
    }
}
