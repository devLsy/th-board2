package study.thboard2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import study.thboard2.domain.vo.BoardVo;
import study.thboard2.domain.vo.CommonVo;
import study.thboard2.domain.vo.PaginationInfo;
import study.thboard2.mapper.BoardMapper;

import java.util.List;

import static study.thboard2.common.utils.ValidationUtil.invokeErrors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService extends CommonService{

    private final BoardMapper boardMapper;

    /**
     * 게시글 목록 조회
     * @param commonVo
     * @return
     * @throws Exception
     */
    public List<BoardVo> getBoardList(CommonVo commonVo) throws Exception{
        return boardMapper.selectBoardList(commonVo);
    }

    /**
     * 게시글 전체 카운트
     * @param commonVo
     * @return
     */
    public int getBoardCnt(CommonVo commonVo) throws Exception{
        return boardMapper.selectBoardCnt(commonVo);
    }

    /**
     * 게시글 상세
     * @param BoardNo
     * @return
     * @throws Exception
     */

    public BoardVo selectBoardDetail(Integer BoardNo) throws Exception{
        return boardMapper.selectBoardDetail(BoardNo);
    }

    /**
     * 게시글 작성
     * @param boardVo
     * @param br
     */
    @Transactional
    public void regBoard(BoardVo boardVo, BindingResult br) throws Exception{

        //parameter 검증 실패 시
        if (br.hasErrors()) {
            log.info("검증 실패");
            invokeErrors(this.getClass().getName(), br);
        }

        boardMapper.insertBoard(boardVo);
    }

    /**
     * 게시글 수정
     * @param boardVo
     */
    @Transactional
    public int modifyBoard(BoardVo boardVo) throws Exception{
        return boardMapper.updateBoard(boardVo);
    }

    /**
     * 게시글 삭제
     * @param boardNo
     * @return
     * @throws Exception
     */
    @Transactional
    public int deleteBoard(Integer boardNo) throws Exception{
        return boardMapper.deleteBoard(boardNo);
    }
}
