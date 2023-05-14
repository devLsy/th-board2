package study.thboard2.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.thboard2.domain.vo.ReplyVo;
import study.thboard2.service.ReplyService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("reply/*")
public class ReplyController {

    private final ReplyService replyService;

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
    @PostMapping("modify/{boardNo}")
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
     * @param replyVo
     * @return
     */
    @GetMapping("detail/{board}")
    public ReplyVo detailReply(@PathVariable Integer boardNo, @RequestBody ReplyVo replyVo) {
        try {
            replyService.getReplyDetail(boardNo, replyVo.getReplyNo());
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        return replyVo;
    }



}
