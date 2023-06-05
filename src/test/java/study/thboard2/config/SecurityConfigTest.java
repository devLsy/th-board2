package study.thboard2.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class SecurityConfigTest {

    @Autowired
    private SecurityConfig securityConfig;

    @Test
    @DisplayName("SecurityConfigTest")
    @Commit
    public void SecurityConfigTest() throws Exception {
        //givin
        String rawPw = "1234";
        String encodePw = securityConfig.bCryptPasswordEncoder().encode(rawPw);
//        log.info("encodePw : " + encodePw);
        String inputPw = "1234";

        //when
        boolean check = securityConfig.bCryptPasswordEncoder().matches(inputPw, encodePw);
//        log.info("check = " + check);
        //then
    }

}