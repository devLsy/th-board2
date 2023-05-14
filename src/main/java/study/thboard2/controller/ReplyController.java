package study.thboard2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.thboard2.domain.vo.ReplyVo;
import study.thboard2.service.ReplyService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글 등록
     * @param boardNo
     * @param replyVo
     * @return
     * @throws Exception
     */
    @PostMapping("/reply/{boardNo}")
    public ResponseEntity saveReply(@PathVariable Integer boardNo, ReplyVo replyVo, RedirectAttributes rttr) throws Exception{
        try {
            replyService.regReply(replyVo);
        } catch (Exception e) {
            log.info("Exception => [{}] ", e.getMessage());
        }
        rttr.addFlashAttribute("replyNo", replyVo.getReplyNo());
        return new ResponseEntity<>(HttpStatus.OK, rttr);
    }




    //댓글 상세





    //댓글 삭제



}
