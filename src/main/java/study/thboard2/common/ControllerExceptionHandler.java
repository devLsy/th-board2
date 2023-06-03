package study.thboard2.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
//컨트롤러 공통 예외처리 클래스
public class ControllerExceptionHandler {

    @ExceptionHandler({Exception.class})
    public void ExceptionHandle(Exception e) {
        log.info("Common Exception = [{}]", e);
    }
}
