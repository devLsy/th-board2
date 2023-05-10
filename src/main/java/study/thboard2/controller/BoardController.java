package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("pages/main");

        try {
            List<BoardVo> boardList = boardService.getBoardList();
            mv.addObject("list", boardList);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return mv;
    }

    /**
     * 게시글 상세/등록 화면
     * @param boardNo
     * @return
     */
    @GetMapping("/reg")
    public ModelAndView detail(@RequestParam(value = "boardNo", required = false) Integer boardNo) {
        ModelAndView mv = new ModelAndView("pages/reg");
        //게시글순번이 있으면 등록, 없으면 수정화면 표시
        if(boardNo != null) {
            try {
                BoardVo boardInfo = boardService.selectBoardDetail(boardNo);
                mv.addObject("boardNo", boardNo);
                mv.addObject("info", boardInfo);
            } catch (Exception e) {
                log.info("Exception => [{}] ", e.getMessage());
            }
        }
        return mv;
    }

    /**
     * 게시글 등록 처리
     * @param boardVo
     * @return
     */
    @PostMapping("/reg")
    public String reg(@ModelAttribute BoardVo boardVo) {
        try {
            boardService.regBoard(boardVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * 게시글 수정 처리
     * @param boardVo
     * @return
     */
    @PostMapping("/mod")
    public String modify(@ModelAttribute BoardVo boardVo) {
        try {
            boardService.modifyBoard(boardVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * 게시글 삭제 처리
     * @param boardNo
     * @return
     */
    public String del(@RequestParam Integer boardNo) {
        try {
            boardService.deleteBoard(boardNo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/";
    }
}
