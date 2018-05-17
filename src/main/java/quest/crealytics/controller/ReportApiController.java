package quest.crealytics.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quest.crealytics.service.IReportService;
import quest.crealytics.vo.ReportVO;
import reactor.core.publisher.Mono;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@RestController
@AllArgsConstructor
@Slf4j
public class ReportApiController {
    private final IReportService iReportService;

    @GetMapping("reports")
    public Mono<ReportVO> getReportAPI1(@RequestParam(required = false) String month, @RequestParam(required = false) String site) {
        return iReportService.getReport(month, site);
    }
}
