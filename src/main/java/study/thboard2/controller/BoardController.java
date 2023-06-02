package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import study.thboard2.common.interceptor.LoginInterceptor;
import study.thboard2.domain.vo.*;
import study.thboard2.service.BoardService;
import study.thboard2.service.FileService;
import study.thboard2.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class BoardController extends CommonController{

    private final BoardService boardService;
    private final ReplyService replyService;
    private final FileService fileService;

    /**
     * 게시글 목록
     * @return
     */
    @GetMapping("")
    public ModelAndView list(@ModelAttribute CommonVo commonVo) {
        ModelAndView mv = new ModelAndView("pages/main");

        try {
            //전체 게시글 수
            int totalCnt = boardService.getBoardCnt(commonVo);
            //페이징 처리 후 반환 객체
            commonVo.setTotalCount(totalCnt);
            PaginationInfo paging = boardService.getPaginationInfo(commonVo);

            commonVo.setFirstRecordIndex(paging.getFirstRecordIndex());
            commonVo.setLastRecordIndex(paging.getLastRecordIndex());

            List<BoardVo> boardList = boardService.getBoardList(commonVo);
            mv.addObject("search", commonVo);
            mv.addObject("list", boardList);
            mv.addObject("paging", paging);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return mv;
    }


    /**
     * 게시글 목록(ajax)
//     * @param commonVo
     * @return
     */
    @PostMapping("listAjax")
    @ResponseBody
//    public ModelAndView listAjax(@ModelAttribute CommonVo commonVo) {
    public void listAjax(@RequestBody HashMap<String, Object> map) {
        ModelAndView mv = new ModelAndView("pages/main");

        try {

            log.info("currentPage : " + map.get("currentPage"));

            //전체 게시글 수
            CommonVo commonVo = new CommonVo();
            commonVo.setCurrentPage((Integer) map.get("currentPage"));
            int totalCnt = boardService.getBoardCnt(commonVo);
//            //페이징 처리 후 반환 객체
            commonVo.setTotalCount(totalCnt);
            PaginationInfo paging = boardService.getPaginationInfo(commonVo);

            commonVo.setFirstRecordIndex(paging.getFirstRecordIndex());
            commonVo.setLastRecordIndex(paging.getLastRecordIndex());

            List<BoardVo> boardList = boardService.getBoardList(commonVo);
//            mv.addObject("search", commonVo);
//            mv.addObject("list", boardList);
//            mv.addObject("paging", paging);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
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
                List<ReplyVo> replyList = replyService.getReplyList(boardNo);
                if (boardInfo != null) {
                    mv.addObject("info", boardInfo);
                    mv.addObject("replyList", replyList);
                }
                List<FileVo> fileList = fileService.getFileList(boardNo);
                mv.addObject("boardNo", boardNo);
                mv.addObject("files", fileList);
            } catch (Exception e) {
                log.info("Exception => [{}] ", e.getMessage());
            }
        }
        return mv;
    }

    /**
     * 게시글 등록 처리
     * @param boardVo
     * @param files
     * @return
     */
    @PostMapping("/reg")
    public String reg(@ModelAttribute BoardVo boardVo, @RequestParam("files")List<MultipartFile> files, HttpSession session) {
        try {
            UserVo userInfo = getUserSessionInfo(session);
            boardVo.setUserId(userInfo.getUserId());
            boardService.regBoard(boardVo);
            fileService.saveFile(files, boardVo.getBoardNo());

        } catch (IOException io) {
            log.info("Exception => [{}] ", io.getMessage());

        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * 게시글 등록 처리(비동기)
     * @param boardVo
     * @param files
     * @return
     */
    @PostMapping(value = "/regAjax", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public Integer regAjax(@RequestPart(value = "boardVo") BoardVo boardVo,
                           @RequestPart(value = "files", required = false) List<MultipartFile> files,
                           HttpSession session) throws IOException, Exception {

        UserVo userInfo = getUserSessionInfo(session);
        boardVo.setUserId(userInfo.getUserId());

        log.info("boardVo = [{}]", boardVo);

        for (MultipartFile file : files) {
            log.info("파일정보 찍혀?");
            log.info("files = [{}]", file.getOriginalFilename());
        }
            log.info("인서트 서비스 찍히니?");
            boardService.regBoard(boardVo);
            fileService.saveFile(files, boardVo.getBoardNo());

        return boardVo.getBoardNo();
    }

    /**
     * 게시글 수정 처리
     * @param boardVo
     * @return
     */
    @PostMapping("/mod")
    public String modify(@ModelAttribute BoardVo boardVo, HttpSession session) {
        try {
            UserVo userInfo = getUserSessionInfo(session);
            boardVo.setUserId(userInfo.getUserId());
            boardService.modifyBoard(boardVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * 게시글 수정(ajax)
     * @param boardVo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping(value = "/modifyAjax", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> modifyAjax(@ModelAttribute BoardVo boardVo, HttpSession session) throws Exception {
        UserVo userInfo = getUserSessionInfo(session);
        boardVo.setUserId(userInfo.getUserId());
        boardService.modifyBoard(boardVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 게시글 삭제 처리
     * @param boardNo
     * @return
     */
    @PostMapping("/del")
    public String del(@RequestParam Integer boardNo) {
        try {
            boardService.deleteBoard(boardNo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return "redirect:/";
    }
}
