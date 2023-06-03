package study.thboard2.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Slf4j
public class ValidationUtil {

    /**
     * 전역 유효성 체크
     * @param className
     * @param br
     */
    public static void invokeErrors(String className, BindingResult br) {

        //유효성 검증 실패 클래스명
        log.error("Validation Error @Class = [{}]", className);

        List<ObjectError> errorList = br.getAllErrors();
        
        //에러 출력
        for (ObjectError err : errorList) {
            log.info("err List");
            log.error(err.toString());
        }
    }
}
