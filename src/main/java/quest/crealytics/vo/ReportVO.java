package quest.crealytics.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@EqualsAndHashCode
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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

}
