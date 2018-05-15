package quest.crealytics.service;

import quest.crealytics.vo.ReportVO;
import reactor.core.publisher.Mono;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
public interface IReportService {
    Mono<ReportVO> getReport(String month, String site);
}
