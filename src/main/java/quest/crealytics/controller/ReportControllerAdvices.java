package quest.crealytics.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import quest.crealytics.exception.MonthNotFoundException;
import quest.crealytics.exception.SiteNotFoundException;
import quest.crealytics.vo.ErrorVO;

/**
 * Created by Pranav S Kurup on 5/15/2018.
 */
@RestControllerAdvice
public class ReportControllerAdvices {
    @ExceptionHandler(MonthNotFoundException.class)
    public ResponseEntity onDateTimeError(MonthNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorVO.builder().msg(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.toString()).build());
    }

    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity onDateTimeError(SiteNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorVO.builder().msg(e.getMessage()).statusCode(HttpStatus.BAD_REQUEST.toString()).build());
    }
}
