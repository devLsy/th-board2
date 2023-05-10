package study.thboard2.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired BoardService boardService;

    @Test
    @Commit
    @DisplayName("게시글목록_조회")
    void getBoardList() {
        boardService.getBoardList();
    }
    
    @Test
    @Commit
    @DisplayName("게시글목록_조회")
    void getBoardCnt() {
    }

    @Test
    @Commit
    @DisplayName("게시글_상세")
    void selectBoardDetail() {
    }

    @Test
    @Commit
    @DisplayName("게시글_등록")
    void regBoard() {
    }

    @Test
    @Commit
    @DisplayName("게시글_수정")
    void modifyBoard() {
    }

    @Test@Commit
    @DisplayName("게시글_삭제")
    void deleteBoard() {
    }
}