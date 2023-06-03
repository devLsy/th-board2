package study.thboard2.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
//게시판 관련 vo
public class BoardVo extends CommonVo{
    
    private Integer no;                             //게시글 rownum
    private Integer boardNo;                       //게시글 순번(시퀀스)
    
    @NotBlank(message = "제목은 꼭 입력해야 해")
    private String title;                       //제목

    @NotBlank(message = "아이디도 반드시 알아야겠지?")
    private String userId;                      //작성자

    @NotBlank(message = "내용도 반드시 알아야겠지?")
    private String content;                     //내용

    private char useYn;                         //사용여부
}
