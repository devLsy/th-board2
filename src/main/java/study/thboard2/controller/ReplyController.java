package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.thboard2.domain.vo.ReplyVo;
import study.thboard2.service.ReplyService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("reply/*")
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 목록
     * @param boardNo
     * @return
     */
    @GetMapping(value = "list/{boardNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ReplyVo> getReplyList(@PathVariable Integer boardNo) {
        List<ReplyVo> replyList = null;
        try {
            replyList = replyService.getReplyList(boardNo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return replyList;
    }

    /**
     * 댓글 등록
     * @param boardNo
     * @param replyVo
     * @return
     * @throws Exception
     */
    @PostMapping(value = "reg/{boardNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Integer saveReply(@PathVariable Integer boardNo, ReplyVo replyVo) throws Exception{
        try {
            replyService.regReply(replyVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return replyVo.getReplyNo();
    }

    /**
     * 댓글 수정
     * @param boardNo
     * @param replyVo
     * @return
     */
    @PostMapping(value = "modify/{boardNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Integer modifyReply(@PathVariable Integer boardNo, ReplyVo replyVo) {
        try {
            replyService.modifyReply(replyVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return replyVo.getReplyNo();
    }

    /**
     * 댓글 상세
     * @param boardNo
     * @param replyNo
     * @return
     */
    @GetMapping(value = "detail/{boardNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReplyVo detailReply(@PathVariable Integer boardNo, @RequestParam Integer replyNo) {

        ReplyVo replyVo = new ReplyVo();
        try {
            replyVo = replyService.getReplyDetail(replyNo, boardNo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return replyVo;
    }

    /**
     * 댓글 삭제
     * @param boardNo
     * @param replyNo
     * @return
     */
    @ResponseBody
    @PostMapping(value = "del/{boardNo}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> delReply(@PathVariable Integer boardNo, @RequestParam Integer replyNo) {
        try {
            int result = replyService.removeReply(boardNo, replyNo);
            if (result == 1) {
                return new ResponseEntity<>(1, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
