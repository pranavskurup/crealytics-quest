package quest.crealytics.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportID;
import quest.crealytics.model.ReportSite;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertTrue;

/**
 * Created by Pranav S Kurup on 5/13/2018.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ReportUtilTest.TestConfiguration.class})
public class ReportUtilTest {

    @Autowired
    private ReportUtil reportUtil;
    private LinkedList<ReportEntity> reports;

    @Test
    public void readFromTestClassPath() {
        List<ReportEntity> reports = reportUtil.readReports();
        assertTrue("Overall records is 16 in test directory", reports.size() == 16);
        this.reports.forEach(reportActual -> {
            AtomicBoolean found = new AtomicBoolean(false);
            reports.forEach(reportCreated -> {
                if (reportActual.equals(reportCreated)) {
                    found.set(true);
                }
            });
            if (!found.get()) {
                log.error("Not found "+reportActual);
            }
            assertTrue(found.get());
        });

    }

    @Test
    public void readFromTestFromFolder() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "data");
        System.setProperty("crealytics.data.dir", resourceDirectory.toString());
        List<ReportEntity> reports = reportUtil.readReports();
        assertTrue("Overall records is 16 in 'crealytics.data.dir' directory", reports.size() == 16);
        this.reports.forEach(reportActual -> {
            AtomicBoolean found = new AtomicBoolean(false);
            reports.forEach(reportCreated -> {
                if (reportActual.equals(reportCreated)) {
                    found.set(true);
                }
            });
            if (!found.get()) {
                log.error("Not found "+reportActual);
            }
            assertTrue(found.get());
        });

    }

    @Test
    public void readFromTestFromEmptyFolder() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        System.setProperty("crealytics.data.dir", resourceDirectory.toString());
        List<ReportEntity> reports = reportUtil.readReports();
        assertTrue("Overall records is 0 in 'crealytics.data.dir' directory", reports.size() == 0);
    }

    @Test
    public void readFromTestFromInvalidRecordsFolder() {
        Path resourceDirectory = Paths.get("src", "test", "resources", "invalid_record");
        System.setProperty("crealytics.data.dir", resourceDirectory.toString());
        List<ReportEntity> reports = reportUtil.readReports();
        assertTrue("Overall valid records is 2 in 'crealytics.data.dir' directory", reports.size() == 2);
    }

    @Before
    public void init() {
        this.reports = new LinkedList<>();
        ReportEntity.ReportEntityBuilder builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("desktop web")).month(Month.JANUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("12483775"))
                .impressions(new BigInteger("11866157"))
                .clicks(new BigInteger("30965"))
                .conversions(new BigInteger("7608"))
                .revenue(new BigDecimal("23555.46"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("mobile web")).month(Month.JANUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("9905942"))
                .impressions(new BigInteger("9401153"))
                .clicks(new BigInteger("25291"))
                .conversions(new BigInteger("6216"))
                .revenue(new BigDecimal("19053.61"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("android")).month(Month.JANUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("9914106"))
                .impressions(new BigInteger("9412958"))
                .clicks(new BigInteger("24395"))
                .conversions(new BigInteger("6018"))
                .revenue(new BigDecimal("18110.41"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("iOS")).month(Month.JANUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("2550165"))
                .impressions(new BigInteger("2419733"))
                .clicks(new BigInteger("6331"))
                .conversions(new BigInteger("1564"))
                .revenue(new BigDecimal("4692.28"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("desktop web")).month(Month.FEBRUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("12483775"))
                .impressions(new BigInteger("11866157"))
                .clicks(new BigInteger("30965"))
                .conversions(new BigInteger("7608"))
                .revenue(new BigDecimal("23555.46"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("mobile web")).month(Month.FEBRUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("9905942"))
                .impressions(new BigInteger("9401153"))
                .clicks(new BigInteger("25291"))
                .conversions(new BigInteger("6216"))
                .revenue(new BigDecimal("19053.61"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("android")).month(Month.FEBRUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("9914106"))
                .impressions(new BigInteger("9412958"))
                .clicks(new BigInteger("24395"))
                .conversions(new BigInteger("6018"))
                .revenue(new BigDecimal("18110.41"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("iOS")).month(Month.FEBRUARY).year(Year.of(2018)).build())
                .requests(new BigInteger("2550165"))
                .impressions(new BigInteger("2419733"))
                .clicks(new BigInteger("6331"))
                .conversions(new BigInteger("1564"))
                .revenue(new BigDecimal("4692.28"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("desktop web")).month(Month.MARCH).year(Year.of(2018)).build())
                .requests(new BigInteger("12483775"))
                .impressions(new BigInteger("11866157"))
                .clicks(new BigInteger("30965"))
                .conversions(new BigInteger("7608"))
                .revenue(new BigDecimal("23555.46"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("mobile web")).month(Month.MARCH).year(Year.of(2018)).build())
                .requests(new BigInteger("9905942"))
                .impressions(new BigInteger("9401153"))
                .clicks(new BigInteger("25291"))
                .conversions(new BigInteger("6216"))
                .revenue(new BigDecimal("19053.61"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("android")).month(Month.MARCH).year(Year.of(2018)).build())
                .requests(new BigInteger("9914106"))
                .impressions(new BigInteger("9412958"))
                .clicks(new BigInteger("24395"))
                .conversions(new BigInteger("6018"))
                .revenue(new BigDecimal("18110.41"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("iOS")).month(Month.MARCH).year(Year.of(2018)).build())
                .requests(new BigInteger("2550165"))
                .impressions(new BigInteger("2419733"))
                .clicks(new BigInteger("6331"))
                .conversions(new BigInteger("1564"))
                .revenue(new BigDecimal("4692.28"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("desktop web")).month(Month.APRIL.APRIL).year(Year.of(2018)).build())
                .requests(new BigInteger("12483775"))
                .impressions(new BigInteger("11866157"))
                .clicks(new BigInteger("30965"))
                .conversions(new BigInteger("7608"))
                .revenue(new BigDecimal("23555.46"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("mobile web")).month(Month.APRIL.APRIL).year(Year.of(2018)).build())
                .requests(new BigInteger("9905942"))
                .impressions(new BigInteger("9401153"))
                .clicks(new BigInteger("25291"))
                .conversions(new BigInteger("6216"))
                .revenue(new BigDecimal("19053.61"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("android")).month(Month.APRIL).year(Year.of(2018)).build())
                .requests(new BigInteger("9914106"))
                .impressions(new BigInteger("9412958"))
                .clicks(new BigInteger("24395"))
                .conversions(new BigInteger("6018"))
                .revenue(new BigDecimal("18110.41"));
        reports.add(builder.build());
        builder = ReportEntity.builder();
        builder.id(ReportID.builder().site(ReportSite.ofSite("iOS")).month(Month.APRIL).year(Year.of(2018)).build())
                .requests(new BigInteger("2550165"))
                .impressions(new BigInteger("2419733"))
                .clicks(new BigInteger("6331"))
                .conversions(new BigInteger("1564"))
                .revenue(new BigDecimal("4692.28"));
        reports.add(builder.build());
    }

    @ContextConfiguration
    @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
    public static class TestConfiguration {


        @Bean
        public ReportUtil reportUtil(Validator validator, ResourcePatternResolver resourceLoader) {
            return new ReportUtil(validator, resourceLoader);
        }
    }
}