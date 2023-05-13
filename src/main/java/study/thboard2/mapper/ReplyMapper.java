package study.thboard2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import study.thboard2.domain.vo.BoardVo;
import study.thboard2.domain.vo.CommonVo;
import study.thboard2.domain.vo.ReplyVo;

import java.util.List;

@Repository @Mapper
public interface ReplyMapper {

    /*댓글 목록 조회*/
    List<ReplyVo> selectReplyList(@Param("boardNo") Integer boardNo);

    /*댓글 전체 카운트*/
    int selectReplyCnt(@Param("boardNo") Integer boardNo);

    /*댓글 상세*/
    ReplyVo selectReplyDetail(@Param("replyNo") Integer replyNo, @Param("boardNo") Integer boardNo);

    /*댓글 작성*/
    void insertReply(ReplyVo replyVo);

    /*댓글 수정*/
    void updateReply(ReplyVo replyVo);

    /*댓글 삭제*/
    void deleteReply(@Param("replyNo") Integer replyNo, @Param("boardNo") Integer boardNo);
}
