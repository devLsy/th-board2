package study.thboard2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.thboard2.domain.vo.BoardVo;
import study.thboard2.mapper.BoardMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     * @return
     */
    public List<BoardVo> getBoardList() {
        return boardMapper.selectBoardList();
    }

    /**
     * 게시글 전체 카운트
     * @return
     */
    public int getBoardCnt() {
        return boardMapper.selectBoardCnt();
    }

    /**
     * 게시글 상세
     * @param BoardNo
     * @return
     */
    public BoardVo selectBoardDetail(Integer BoardNo) {
        return boardMapper.selectBoardDetail(BoardNo);
    }

    /**
     * 게시글 작성
     * @param boardVo
     */
    public void regBoard(BoardVo boardVo) {
        boardMapper.insertBoard(boardVo);
    }

    /**
     * 게시글 수정
     * @param boardVo
     */
    public void modifyBoard(BoardVo boardVo) {
        boardMapper.updateBoard(boardVo);
    }

    /**
     * 게시글 삭제
     * @param boardNo
     */
    public void deleteBoard(Integer boardNo) {
        boardMapper.deleteBoard(boardNo);
    }

}
