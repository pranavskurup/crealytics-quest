package quest.crealytics.repo;

import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportSite;
import reactor.core.publisher.Flux;

import java.time.Month;
import java.util.List;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
public interface IReportRepo {
    Flux<ReportEntity> findReportEntityByMonthAndSite(Month month, ReportSite site);

    void populate(List<ReportEntity> reports);
}
