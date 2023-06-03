package study.thboard2.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.thboard2.domain.vo.UserVo;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired UserService userService;

    @Test
    @DisplayName("사용자등록")
    @Commit
//    @Disabled
    public void 사용자등록() throws Exception {
        //givin
        UserVo userVo = new UserVo();
        userVo.setUserId("devlsy86");
        userVo.setUserEmail("devlsy86@naver.com");
        userVo.setUserPassword("1234");
        userVo.setUserName("꼬비");

        userService.regUser(userVo);
        //when

        //then 
    }

}