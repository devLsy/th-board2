package study.thboard2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.thboard2.domain.vo.BoardVo;
import study.thboard2.mapper.BoardMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     * @return
     */
    public List<BoardVo> getBoardList() throws Exception{
        return boardMapper.selectBoardList();
    }

    /**
     * 게시글 전체 카운트
     * @return
     */
    public int getBoardCnt() throws Exception{
        return boardMapper.selectBoardCnt();
    }

    /**
     * 게시글 상세
     * @param BoardNo
     * @return
     */
    public BoardVo selectBoardDetail(Integer BoardNo) throws Exception{
        return boardMapper.selectBoardDetail(BoardNo);
    }

    /**
     * 게시글 작성
     * @param boardVo
     */
    @Transactional
    public void regBoard(BoardVo boardVo) throws Exception{
        boardMapper.insertBoard(boardVo);
    }

    /**
     * 게시글 수정
     * @param boardVo
     */
    @Transactional
    public void modifyBoard(BoardVo boardVo) throws Exception{
        boardMapper.updateBoard(boardVo);
    }

    /**
     * 게시글 삭제
     * @param boardNo
     */
    @Transactional
    public void deleteBoard(Integer boardNo) throws Exception{
        boardMapper.deleteBoard(boardNo);
    }

}
