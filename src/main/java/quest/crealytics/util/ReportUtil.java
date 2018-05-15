package quest.crealytics.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import quest.crealytics.model.ReportEntity;

import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@Slf4j
@Component
@AllArgsConstructor
public class ReportUtil {

    private static final String YEAR_PATTERN = "((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";    // Year 1
    private static final String UNDERSCORE_PATTERN = "(_)";      // '_' Single Character 1
    private static final String MONTH_PATTERN = "(0[1-9]|1[0-2])";    // Integer Number 01 -12
    private static final String REPORT_NAME_PATTERN = "(report)";    // Variable Name 1
    private static final String DOT_PATTERN = "(\\.)";    // '.' Single Character 2
    private static final String FILE_EXTENSION_PATTERN = "(csv)";    // 'csv' Variable Name 2
    private static final String REPORT_FILE_NAME_PATTERN = YEAR_PATTERN + UNDERSCORE_PATTERN + MONTH_PATTERN + UNDERSCORE_PATTERN + REPORT_NAME_PATTERN + DOT_PATTERN + FILE_EXTENSION_PATTERN;
    private static final Pattern PATTERN = Pattern.compile(REPORT_FILE_NAME_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);


    private final Validator validator;
    private final ResourcePatternResolver resourceLoader;

    public List<ReportEntity> readReports() {
        String dataDir = System.getProperty("crealytics.data.dir");
        if (dataDir == null || dataDir.isEmpty()) {
            log.info("Loading from classpath 'data' directory");
            return getResourceFolderFiles("data");
        } else {
            log.info("Loading from '{}' directory", dataDir);
            return getResourceFolderFilesFromUrl(new File(dataDir)).stream().map(getReportEntities()).flatMap(List::stream).collect(Collectors.toList());
        }
    }

    private List<ReportEntity> getResourceFolderFiles(String folder) {
        List<ReportEntity> retVal = new LinkedList<>();
        try {
            Resource[] files = resourceLoader.getResources("classpath:" + folder + "/*.csv");
            if (files != null) {
                retVal = Stream.of(files).filter(resource -> resource.exists() && isReport(resource.getFilename())).flatMap(resource -> {
                    try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                        return readEntityFromStream(reader, resource.getFilename()).stream();
                    } catch (IOException e) {
                        log.error("Error occured while accessing " + resource + "inside jar", e);
                    }
                    return null;
                }).filter(reportEntities -> reportEntities != null).collect(Collectors.toList());
            }
        } catch (IOException e) {
            log.error("Error occured while accessing folder " + folder + "inside jar", e);
        }
        return retVal;
    }

    private boolean isReport(String filename) {
        return PATTERN.matcher(filename).find();
    }

    private boolean getMonth(String filename) {
        Matcher matcher = PATTERN.matcher(filename);
        matcher.find();
        return MonthUtil.of(matcher.group(3));
    }

    public static Year getYear(String filename) {
        Matcher matcher = PATTERN.matcher(filename);
        matcher.find();
        return Year.of(Integer.parseInt(matcher.group(1)));
    }
    private Arrays readEntityFromStream(InputStreamReader reader, String filename) {
        return null;
    }

}
