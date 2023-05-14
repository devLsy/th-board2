package study.thboard2.domain.vo;

import lombok.Data;

@Data
//게시판 관련 vo
public class BoardVo extends CommonVo{

    private Integer no;                             //게시글 rownum
    private Integer boardNo;                       //게시글 순번(시퀀스)
    private String title;                       //제목
    private String userId;                      //작성자
    private String content;                     //내용
    private char useYn;                         //사용여부
}
