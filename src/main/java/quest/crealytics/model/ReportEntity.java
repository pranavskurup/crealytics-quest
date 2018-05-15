package quest.crealytics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.util.function.BiFunction;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Data
@Builder
@Entity
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity implements Serializable {
    private static final long serialVersionUID = -3938006942330436676L;

    @EmbeddedId
    @NotNull
    private ReportID id;

    @Column
    @Range(min = 0l, message = "Requests accepts only positive number")
    private BigInteger requests;

    @Column
    @Range(min = 0l, message = "Impressions accepts only positive number")
    private BigInteger impressions;

    @Column
    @Range(min = 0l, message = "Clicks accepts only positive number")
    private BigInteger clicks;

    @Column
    @Range(min = 0l, message = "Conversions accepts only positive number")
    private BigInteger conversions;

    @Column
    @Range(min = 0l, message = "Revenue accepts only positive number")
    private BigDecimal revenue;

    public static BiFunction<ReportEntity, ReportEntity, ReportEntity> remap(Month monEnum, ReportSite siteEnum) {
        return (en1, en2) -> {
            ReportEntityBuilder builder = ReportEntity.builder();
            ReportID.ReportIDBuilder idBuilder = ReportID.builder();
            if (monEnum != null) {
                idBuilder.month(monEnum);
            }
            if (siteEnum != null) {
                idBuilder.site(siteEnum);
            }
            builder.id(idBuilder.build());
            return builder.
                    requests(en1.requests.add(en2.requests)).
                    impressions(en1.impressions.add(en2.impressions)).
                    clicks(en1.clicks.add(en2.clicks)).
                    conversions(en1.conversions.add(en2.conversions)).
                    revenue(en1.revenue.add(en2.revenue)).
                    build();
        };
    }
}
