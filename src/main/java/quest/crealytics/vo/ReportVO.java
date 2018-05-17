package quest.crealytics.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportSite;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Month;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@EqualsAndHashCode
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class ReportVO implements Serializable {
    private static final long serialVersionUID = 1365381642964405797L;

    @JsonIgnore
    private String year;

    private String month;

    private String site;

    @Builder.Default
    private BigInteger requests = BigInteger.ZERO;

    @Builder.Default
    private BigInteger impressions = BigInteger.ZERO;

    @Builder.Default
    private BigInteger clicks = BigInteger.ZERO;

    @Builder.Default
    private BigInteger conversions = BigInteger.ZERO;

    @Builder.Default
    private BigDecimal revenue = BigDecimal.ZERO;


    @Builder.Default
    @JsonProperty("CTR")
    private Double ctr = 0d;


    @Builder.Default
    @JsonProperty("CR")
    private Double cr = 0d;

    @Builder.Default
    @JsonProperty("fill_rate")
    private Double fillRate = 0d;

    @Builder.Default
    @JsonProperty("eCPM")
    private Double ecpm = 0d;

    public static ReportVO createFromEntity(ReportEntity reportEntity) {
        Assert.notNull(reportEntity, "Report entity should not be null");
        log.debug("Converting Report Entity: {} to ReportVO ", reportEntity);
        ReportVOBuilder builder = ReportVO.builder();
        if (reportEntity.getId().getYear() != null) {
            builder.year(reportEntity.getId().getYear().toString());
        } else {
            log.debug("Since year is null we will set year as 'all'");
            builder.year("all");
        }
        if (reportEntity.getId().getMonth() != null) {
            String month = reportEntity.getId().getMonth().name();
            builder.month(month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase());
        } else {
            log.debug("Since month is null we will set month as 'all'");
            builder.month("all");
        }
        if (reportEntity.getId().getSite() != null) {
            builder.site(reportEntity.getId().getSite().getSiteTypeViewName());
        } else {
            log.debug("Since site is null we will set site as 'all'");
            builder.site("all");
        }
        builder.requests(reportEntity.getRequests());
        builder.impressions(reportEntity.getImpressions());
        builder.clicks(reportEntity.getClicks());
        builder.conversions(reportEntity.getConversions());
        builder.revenue(reportEntity.getRevenue());
        log.debug("Calculating CTR, CR, eCPM and fill_rate");
        builder.calculate();
        ReportVO reportVO = builder.build();
        log.debug("Generated ReportVO {}", reportVO);
        return reportVO;
    }

    public static class ReportVOBuilder {

        public static double round(double value, int places) {
            if (places < 0) throw new IllegalArgumentException();

            BigDecimal bd = new BigDecimal(String.valueOf(value));
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }

        public ReportVOBuilder empty(Month monEnum, ReportSite siteEnum) {
            if (monEnum != null) {
                String month = monEnum.name();
                month(month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase());
            } else {
                month("all");
            }
            if (siteEnum != null) {
                site(siteEnum.getSiteTypeViewName());
            } else {
                site("all");
            }
            return this;
        }

        public ReportVOBuilder calculate() {
            ctr(round((clicks.doubleValue() / impressions.doubleValue()) * 100, 2));
            cr(round((conversions.doubleValue() / impressions.doubleValue()) * 100, 2));
            fillRate(round((impressions.doubleValue() / requests.doubleValue()) * 100, 2));
            ecpm(round((revenue.doubleValue() / impressions.doubleValue()) * 1000, 2));
            return this;
        }
    }

}
