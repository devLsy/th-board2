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
    void getBoardList() throws Exception {
        boardService.getBoardList();
    }

    @Test
    @Commit
    @DisplayName("게시글목록_카운트")
    @Disabled
    void getBoardCnt() throws Exception {
        boardService.getBoardCnt();
    }

    @Test
    @Commit
    @DisplayName("게시글_등록")
    @Disabled
    void regBoard() throws Exception {
        BoardVo boardVo = new BoardVo();
        boardVo.setTitle("게시판으로 기초를 다지자");
        boardVo.setContent("게시판 반복 개발");
        boardVo.setWriter("제노피스");
        boardService.regBoard(boardVo);
    }

    @Test
    @Commit
    @DisplayName("게시글_수정")
//    @Disabled
    void modifyBoard() throws Exception {
        BoardVo boardVo = new BoardVo();
        boardVo.setBoardNo(1);
        boardVo.setTitle("우유");
        boardVo.setContent("우유 맛있어");
        boardVo.setWriter("밀크");
        boardService.modifyBoard(boardVo);
    }

    @Test@Commit
    @DisplayName("게시글_삭제")
    @Disabled
    void deleteBoard() throws Exception {
        BoardVo boardVo = new BoardVo();
        boardVo.setBoardNo(1);
        boardService.deleteBoard(boardVo.getBoardNo());
    }
}