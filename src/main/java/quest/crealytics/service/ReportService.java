package quest.crealytics.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportSite;
import quest.crealytics.repo.IReportRepo;
import quest.crealytics.util.MonthUtil;
import quest.crealytics.vo.ReportVO;
import reactor.core.publisher.Mono;

import java.time.Month;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Service
@AllArgsConstructor
@Slf4j
public class ReportService implements IReportService {

    private final IReportRepo reportRepo;

    @Override
    public Mono<ReportVO> getReport(String month, String site) {
        log.info("Retrieve ReportVO object with respect to month '{}' and site '{}'", month, site);
        Month monEnum = null;
        if (month != null && !month.equals("all")) {
            monEnum = MonthUtil.of(month);
        }

        ReportSite siteEnum = null;
        if (site != null && !site.equals("all")) {
            siteEnum = ReportSite.ofSite(site);
        }
        return reportRepo.findReportEntityByMonthAndSite(monEnum, siteEnum).reduce(ReportEntity.remap(monEnum, siteEnum)).map(ReportVO::createFromEntity).defaultIfEmpty(ReportVO.builder().empty(monEnum, siteEnum).build());
    }
}
