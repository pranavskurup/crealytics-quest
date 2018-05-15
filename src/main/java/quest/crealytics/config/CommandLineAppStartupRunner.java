package quest.crealytics.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import quest.crealytics.repo.IReportRepo;
import quest.crealytics.util.ReportUtil;

import javax.transaction.Transactional;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Component
@Slf4j
@Transactional
@AllArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final IReportRepo iReportRepo;

    private final ReportUtil reportUtil;

    @Override
    public void run(String... args) throws Exception {
        iReportRepo.populate(reportUtil.readReports());
    }
}
