package study.thboard2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import study.thboard2.domain.vo.UserVo;
import study.thboard2.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class CommonController {

    @Autowired
    UserService userService;

    public UserVo getUserSessionInfo(HttpSession session) throws Exception {
        String id = (String) session.getAttribute("id");
        return userService.getUserDetail(id);
    }
}
