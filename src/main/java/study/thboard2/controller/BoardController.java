package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import study.thboard2.domain.vo.*;
import study.thboard2.service.BoardService;
import study.thboard2.service.FileService;
import study.thboard2.service.ReplyService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ModelAndView list(@ModelAttribute CommonVo commonVo) throws Exception {
        ModelAndView mv = new ModelAndView("pages/main");

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

        return mv;
    }


    /**
     * 게시글 목록(ajax)
     * @param commonVo
     * @return
     */
    @PostMapping("listAjax")
    @ResponseBody
    public ResponseEntity<?> listAjax(@ModelAttribute("commonVo") CommonVo commonVo) throws Exception {
        ModelAndView mv = new ModelAndView("pages/main");

        Map<String, Object> map = new HashMap<>();
        //전체 게시글 수
        int totalCnt = boardService.getBoardCnt(commonVo);
        commonVo.setTotalCount(totalCnt);
        //페이징 처리 후 반환 객체
        PaginationInfo paging = boardService.getPaginationInfo(commonVo);
        log.info("commonVo = [{}]", commonVo);
        log.info("paging = [{}]", paging);
        commonVo.setFirstRecordIndex(paging.getFirstRecordIndex());
        commonVo.setLastRecordIndex(paging.getLastRecordIndex());

        List<BoardVo> boardList = boardService.getBoardList(commonVo);

        map.put("list", boardList);
        map.put("paging", paging);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * 게시글 상세/등록 화면
     * @param boardNo
     * @return
     */
    @GetMapping("/reg")
    public ModelAndView detail(@RequestParam(value = "boardNo", required = false) Integer boardNo, HttpSession session) throws Exception {

        ModelAndView mv = new ModelAndView("pages/reg");
        UserVo useVo = getUserSessionInfo(session);
        //게시글순번이 있으면 등록, 없으면 수정화면 표시
        if(boardNo != null) {
            BoardVo boardInfo = boardService.selectBoardDetail(boardNo);
            List<ReplyVo> replyList = replyService.getReplyList(boardNo);
            if (boardInfo != null) {
                mv.addObject("info", boardInfo);
                mv.addObject("userVo", useVo);
                mv.addObject("replyList", replyList);
            }
            List<FileVo> fileList = fileService.getFileList(boardNo);
            mv.addObject("boardNo", boardNo);
            mv.addObject("files", fileList);
        }
        return mv;
    }

    /**
     * 게시글 등록 처리
     * @param boardVo
     * @param files
     * @param br
     * @return
     */
    @PostMapping("/reg")
    public String reg(@ModelAttribute BoardVo boardVo, @RequestParam("files")List<MultipartFile> files, BindingResult br, HttpSession session) throws Exception {

        UserVo userInfo = getUserSessionInfo(session);
        boardVo.setUserId(userInfo.getUserId());
        boardService.regBoard(boardVo, br);
        fileService.saveFile(files, boardVo.getBoardNo(), br);

        return "redirect:/";
    }

    /**
     * 게시글 등록 처리(비동기)
     * @param boardVo
     * @param files
     * @param br
     * @param session
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/regAjax")
    @ResponseBody
    public Integer regAjax(@Valid @RequestPart(value = "boardVo") BoardVo boardVo,
                           @RequestPart(value = "files", required = false) List<MultipartFile> files,
                           BindingResult br,
                           HttpSession session) throws Exception {

        UserVo userInfo = getUserSessionInfo(session);
        boardVo.setUserId(userInfo.getUserId());
        
        boardService.regBoard(boardVo, br);
        fileService.saveFile(files, boardVo.getBoardNo(), br);

        return boardVo.getBoardNo();
    }

    /**
     * 게시글 수정 처리
     * @param boardVo
     * @return
     */
    @PostMapping("/mod")
    public String modify(@ModelAttribute BoardVo boardVo, HttpSession session) throws Exception {

        UserVo userInfo = getUserSessionInfo(session);
        boardVo.setUserId(userInfo.getUserId());
        boardService.modifyBoard(boardVo);

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

        int result = boardService.modifyBoard(boardVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 게시글 삭제 처리
     * @param boardNo
     * @return
     */
    @PostMapping("/del")
    public String del(@RequestParam Integer boardNo) throws Exception {

        int result = boardService.deleteBoard(boardNo);
        log.info("result = [{}]", result);
        return "redirect:/";
    }
}
