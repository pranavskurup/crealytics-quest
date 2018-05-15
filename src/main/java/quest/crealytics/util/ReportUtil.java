package quest.crealytics.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import quest.crealytics.model.ReportEntity;
import quest.crealytics.model.ReportHeader;
import quest.crealytics.model.ReportID;
import quest.crealytics.model.ReportSite;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
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

    private static boolean isReport(String filename) {
        return PATTERN.matcher(filename).find();
    }

    private static Month getMonth(String filename) {
        Matcher matcher = PATTERN.matcher(filename);
        matcher.find();
        return MonthUtil.of(matcher.group(3));
    }

    public static Year getYear(String filename) {
        Matcher matcher = PATTERN.matcher(filename);
        matcher.find();
        return Year.of(Integer.parseInt(matcher.group(1)));
    }

    public List<ReportEntity> readReports() {
        String dataDir = System.getProperty("crealytics.data.dir");
        if (dataDir == null || dataDir.isEmpty()) {
            log.info("Loading from classpath 'data' directory");
            return getResourceFolderFiles("data");
        } else {
            log.info("Loading from '{}' directory", dataDir);
            return getResourceFolderFilesFromFile(new File(dataDir)).stream().map(getReportEntities()).flatMap(List::stream).collect(Collectors.toList());
        }
    }

    private Function<File, List<ReportEntity>> getReportEntities() {
        return (File file) -> {
            try (FileReader reader = new FileReader(file);) {
                return readEntityFromStream(reader, file.getName());
            } catch (FileNotFoundException e) {
                log.error("FileNotFoundException occured while reading file " + file, e);
            } catch (IOException e) {
                log.error("IOException occured while reading file " + file, e);
            }
            return new ArrayList<>();
        };
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
                }).filter(Objects::nonNull).collect(Collectors.toList());
            }
        } catch (IOException e) {
            log.error("Error occured while accessing folder " + folder + "inside jar", e);
        }
        return retVal;
    }

    private List<ReportEntity> readEntityFromStream(InputStreamReader reader, String fileName) {
        log.info("Generating report entity from 'csv' file");
        List<ReportEntity> reportEntities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(reader);) {
            String line = "";
            String cvsSplitBy = ",";
            String[] header = null;
            while ((line = br.readLine()) != null) {
                String[] lineSplitted = line.trim().split(cvsSplitBy);
                if (header == null) {
                    header = lineSplitted;
                } else {
                    Optional<ReportEntity> entity = getReportEntityFromString(header, lineSplitted);
                    if (entity.isPresent()) {
                        ReportEntity reportEntity = entity.get();
                        ReportID id = reportEntity.getId();
                        id.setMonth(getMonth(fileName));
                        id.setYear(getYear(fileName));
                        reportEntities.add(reportEntity);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Report file not found", e);
        } catch (IOException e) {
            log.error("Error occured while reading Report file", e);
        }
        return reportEntities;
    }

    private Optional<ReportEntity> getReportEntityFromString(String[] headers, String[] line) {
        Optional<ReportEntity> reportEntity = Optional.empty();
        ReportEntity.ReportEntityBuilder builder = ReportEntity.builder();
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].trim();
            String currentLine = line[i];
            build(builder, currentLine, header);
        }
        ReportEntity entity = builder.build();
        Set<ConstraintViolation<ReportEntity>> violations = validator.validate(entity);
        if (violations.isEmpty()) {
            reportEntity = Optional.of(entity);
        } else {
            for (ConstraintViolation<ReportEntity> violation : violations) {
                log.error(violation.getMessage());
            }
        }
        return reportEntity;
    }

    private void build(ReportEntity.ReportEntityBuilder builder, String currentLine, String currentHeader) {
        if (currentLine == null || currentHeader == null) {
            return;
        }
        ReportHeader reportHeader = ReportHeader.ofHeader(currentHeader);
        Assert.notNull(reportHeader, "Unable to find report header for " + currentHeader);
        currentLine = currentLine.trim();
        ReportID.ReportIDBuilder idBuilder = ReportID.builder();
        switch (reportHeader) {
            case SITE:
                idBuilder.site(ReportSite.ofSite(currentLine));
                builder.id(idBuilder.build());
                break;
            case REQUESTS:
                if (NumberUtils.isCreatable(currentLine))
                    builder.requests(new BigInteger(currentLine));
                break;
            case IMPRESSIONS:
                if (NumberUtils.isCreatable(currentLine))
                    builder.impressions(new BigInteger(currentLine));
                break;
            case CLCIKS:
                if (NumberUtils.isCreatable(currentLine))
                    builder.clicks(new BigInteger(currentLine));
                break;
            case CONVERSIONS:
                if (NumberUtils.isCreatable(currentLine))
                    builder.conversions(new BigInteger(currentLine));
                break;
            case REVENUE:
                if (NumberUtils.isCreatable(currentLine))
                    builder.revenue(new BigDecimal(currentLine));
                break;
            default:
                break;
        }
    }

    private List<File> getResourceFolderFilesFromFile(File dataDir) {
        List<File> retVal = new LinkedList<>();
        if (dataDir != null && dataDir.isDirectory()) {
            File[] reportFiles = dataDir.listFiles(pathname -> isReport(pathname.getName()) && pathname.isFile());
            if (reportFiles != null && reportFiles.length != 0) {
                retVal = Stream.of(reportFiles).collect(Collectors.toList());
            }
        }
        return retVal;
    }

}
