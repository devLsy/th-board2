package study.thboard2.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.thboard2.domain.vo.ReplyVo;
import study.thboard2.mapper.BoardMapper;
import study.thboard2.mapper.ReplyMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService extends CommonService{

    private final ReplyMapper replyMapper;
    private final BoardMapper boardMapper;

    /**
     * 댓글 목록 조회
     * @param boardNo
     * @return
     */
    public List<ReplyVo> getReplyList(Integer boardNo) throws Exception {
        return replyMapper.selectReplyList(boardNo);
    }

    /**
     * 댓글 전체 카운트
     * @param boardNo
     * @return
     */
    public int getReplyCnt(Integer boardNo) throws Exception {
        return replyMapper.selectReplyCnt(boardNo);
    }

    /**
     * 댓글 상세
     * @param boardNo
     * @return
     */
    public ReplyVo getReplyDetail(Integer replyNo, Integer boardNo) throws Exception {
        return replyMapper.selectReplyDetail(replyNo, boardNo);
    }

    /**
     * 댓글 작성
     * @param replyVo
     */
    @Transactional
    public void regReply(ReplyVo replyVo) throws Exception{
        int result = replyMapper.insertReply(replyVo);
        if(result == 1) boardMapper.updateReplyCnt(replyVo.getBoardNo());
    }

    /**
     * 댓글 수정
     * @param replyVo
     */
    @Transactional
    public void modifyReply(ReplyVo replyVo) throws Exception{
        replyMapper.updateReply(replyVo);
    }

    /**
     * 댓글 삭제
     * @param boardNo
     * @param replyNo
     * @return
     * @throws Exception
     */
    @Transactional
    public int removeReply(Integer boardNo, Integer replyNo) throws Exception{
        return replyMapper.deleteReply(replyNo, boardNo);
    }


}
