package study.thboard2.domain.vo;

import lombok.Data;

@Data
public class BoardVo {

    private Long boardNo;                       //게시글 순번
    private String title;                       //제목
    private String writer;                      //작성자
    private String content;                     //내용
    private char useYn;                         //사용여부
    private String regDate;                     //등록일
    private String modDate;                     //수정일
}
