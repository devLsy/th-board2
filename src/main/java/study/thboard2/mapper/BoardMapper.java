package study.thboard2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import study.thboard2.domain.vo.BoardVo;
import study.thboard2.domain.vo.CommonVo;

import java.util.List;

@Repository @Mapper
public interface BoardMapper {

    /*게시글 목록 조회*/
    List<BoardVo> selectBoardList(CommonVo commonVo);

    /*게시글 전체 카운트*/
    int selectBoardCnt(CommonVo commonVo);

    /*게시글 상세*/
    BoardVo selectBoardDetail(@Param("boardNo") Integer boardNo);

    /*게시글 작성*/
    void insertBoard(BoardVo boardVo);

    /* 게시글 작성(merge) */
    void mergeBoard(BoardVo boardVo);

    /*게시글 수정*/
    int updateBoard(BoardVo boardVo);

    /* 댓글 cnt 업데이트 */
    int updateReplyCnt(@Param("boardNo") Integer boardNo);

    /*게시글 삭제*/
    int deleteBoard(@Param("boardNo") Integer boardNo);
}
