package quest.crealytics.vo;

import org.junit.Assert;
import org.junit.Test;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportID;
import quest.crealytics.model.ReportSite;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.time.Year;

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
                .ctr(0.2609522189871582d)
                .cr(0.06411511325865653d)
                .fillRate(95.05263431934651d)
                .ecpm(1.9850959329123994).build();
        Assert.assertTrue(expectedVo.equals(reportVO));
    }
}