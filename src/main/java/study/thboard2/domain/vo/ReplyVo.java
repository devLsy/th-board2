package study.thboard2.domain.vo;

import lombok.Data;

@Data
//댓글 vo
public class ReplyVo extends CommonVo{

    private Integer replyNo;           //댓글 순번(시퀀스)
    private Integer boardNo;           //게시글 순번(fk)
    private String replyContent;       //댓글 내용
    private String replyWriter;        //댓글 작성자
    private char denYn;                //삭제여부
}
