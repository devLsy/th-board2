package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.thboard2.domain.vo.UserVo;
import study.thboard2.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
        String id = (String) session.getAttribute("id");
        log.info("stored session id =[{}]", id);
        return id != null ? "redirect:/" : "pages/login" ;
    }

    /**
     * 회원가입 화면
     * @return
     */
    @GetMapping("/register")
    public String registerForm() {
        return "pages/register";
    }

    /**
     * 회원가입 처리
     * @param userVo
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public String register(@ModelAttribute UserVo userVo) {
        try {
            userService.regUser(userVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/login";
    }

    /**
     * 회원가입 처리(ajax)
     * @param userVo
     * @return
     */
    @ResponseBody
    @PostMapping("/registerAjax")
    public ResponseEntity registerAjax(@ModelAttribute UserVo userVo) throws Exception {

        log.info("userVo = [{}]", userVo);

        int result = userService.regUser(userVo);

        if (result == 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(2, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 아이디 체크
     * @param
     * @return
     */
    @GetMapping("/idCheck/{userId}")
    @ResponseBody
    public ResponseEntity<?> idCheck(@PathVariable String userId) throws Exception {

        log.info("userId = [{}]", userId);
        //1이면 아이디 존재, 2면 미존재
        int cnt = userService.selectIdCnt(userId);

        log.info("cnt = [{}]", cnt);

        if (cnt == 1) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
        return new ResponseEntity<>(2, HttpStatus.OK);
    }

    /**
     * 사용자 로그인
     * @param userId
     * @param userPassword
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam String userId,
                                @RequestParam String userPassword,
                                HttpSession session) throws Exception {

        String id = userService.login(userId, userPassword);
        if(id == "none") return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);

        session.setAttribute("id", id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    /**
     * 로그아웃 처리
     * @param session
     * @return
     */
    @PostMapping("logout")
    public String logout(HttpSession session) throws Exception{
        session.invalidate();
        return "redirect:/login";
    }
}
