package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import study.thboard2.domain.vo.BoardVo;
import study.thboard2.service.BoardService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록
     * @return
     */
    @GetMapping("")
    public ModelAndView list() throws Exception {
        ModelAndView mv = new ModelAndView("pages/main");

        try {
            List<BoardVo> boardList = boardService.getBoardList();
            mv.addObject("list", boardList);
        } catch (Exception e) {
            throw new Exception("시스템 에러입니다.", e);
        }
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
