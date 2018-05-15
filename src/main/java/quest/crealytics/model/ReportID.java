package quest.crealytics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Month;
import java.time.Year;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportID implements Serializable {
    private static final long serialVersionUID = -1648043182823391693L;
    @Column
    @NotNull
    private Year year;

    @Column
    @NotNull
    private Month month;

    @NotNull
    @Column
    private ReportSite site;
}
