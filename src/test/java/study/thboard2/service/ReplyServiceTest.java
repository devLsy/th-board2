package study.thboard2.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.thboard2.domain.vo.ReplyVo;

@SpringBootTest
@Slf4j
class ReplyServiceTest {

    @Autowired ReplyService replyService;

    @Test
    @Commit
    @DisplayName("목록")//성공
    @Disabled
    void getReplyList() {
//        replyService.getReplyList(2);
    }

    @Test
    @Commit
    @DisplayName("카운트")//성공
    @Disabled
    void getReplyCnt() {
//        replyService.getReplyCnt(2);
    }

    @Test
    @Commit
    @DisplayName("상세")//성공
    @Disabled
    void getReplyDetail() {
//        ReplyVo replyDetail = replyService.getReplyDetail(1, 2);
//        Assertions.assertEquals(1, replyDetail.getReplyNo());
    }

    @Test
    @Commit
    @DisplayName("등록")//성공
//    @Disabled
    void regReply() {
        ReplyVo replyVo = new ReplyVo();
        replyVo.setBoardNo(2);
        replyVo.setReplyContent("첫번 째 댓글 테스트");
        replyVo.setUserId("lsy");
//        replyService.regReply(replyVo);
    }

    @Test
    @Commit
    @DisplayName("수정")//성공
    @Disabled
    void modifyReply() {
        ReplyVo replyVo = new ReplyVo();
        replyVo.setReplyNo(1);
        replyVo.setBoardNo(2);
        replyVo.setReplyContent("댓글 수정이다.");
//        replyService.modifyReply(replyVo);
    }

    @Test
    @Commit
    @DisplayName("삭제")//성공
    @Disabled
    void removeReply() {
        ReplyVo replyVo = new ReplyVo();
        replyVo.setReplyNo(1);
        replyVo.setBoardNo(2);
//        replyService.removeReply(replyVo.getReplyNo(), replyVo.getBoardNo());
    }
}