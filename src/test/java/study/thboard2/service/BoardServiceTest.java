package study.thboard2.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.thboard2.domain.vo.BoardVo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired BoardService boardService;

    @Test
    @Commit
    @DisplayName("게시글목록_조회")
    @Disabled
    void getBoardList() {
        boardService.getBoardList();
    }

    @Test
    @Commit
    @DisplayName("게시글목록_카운트")
    @Disabled
    void getBoardCnt() {
        boardService.getBoardCnt();
    }

    @Test
    @Commit
    @DisplayName("게시글_등록")
    @Disabled
    void regBoard() {
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("게시판으로 기초를 다지자");
        boardVo.setContent("게시판 반복 개발");
        boardVo.setWriter("제노피스");
        boardService.regBoard(boardVo);
    }

    @Test
    @Commit
    @DisplayName("게시글_수정")
    @Disabled
    void modifyBoard() {
        BoardVo boardInfo = boardService.selectBoardDetail(1);
        boardInfo.setBoardNo(boardInfo.getBoardNo());
        boardInfo.setTitle("나라말싸미");
        boardInfo.setContent("궁구게");
        boardInfo.setWriter("한비광");
        boardService.modifyBoard(boardInfo);
    }

    @Test@Commit
    @DisplayName("게시글_삭제")
    @Disabled
    void deleteBoard() {
        BoardVo boardVo = new BoardVo();
        boardVo.setBoardNo(1);
        boardService.deleteBoard(boardVo.getBoardNo());
    }
}