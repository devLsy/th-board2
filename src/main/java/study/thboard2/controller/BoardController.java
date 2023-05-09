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
     * 게시글 목록
     * @return
     */
    @GetMapping("")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("pages/main");
        return mv;
    }

    /**
     * 게시글 상세
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail() {
        ModelAndView mv = new ModelAndView("pages/detail");
        return mv;
    }
}
