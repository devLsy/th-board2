package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class BoardController {

    /**
     * 게시글 목록 조회
     * @return
     */
    @GetMapping("/")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("pages/list");
        return mv;
    }
}
