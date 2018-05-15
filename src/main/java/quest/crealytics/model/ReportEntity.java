package quest.crealytics.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Data
@Builder
@Entity
@Table(name = "report")
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

}
