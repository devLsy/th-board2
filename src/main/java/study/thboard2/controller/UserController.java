package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.thboard2.domain.vo.UserVo;
import study.thboard2.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 로그인 화면
     * @param session
     * @return
     */
    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        String userId = String.valueOf(session.getAttribute("userId"));
        log.info("stored session id =[{}]", userId);
        return (userId != null) ? "redirect:/" : "/login";
    }

    /**
     * 사용자 로그인
     * @param userId
     * @param userPassword
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String userPassword,
                        HttpSession session) {
        String id = userService.login(userId, userPassword);
        return id != null ? "redirect:/" : "/login";
    }
}
