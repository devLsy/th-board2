package study.thboard2.domain.vo;

import lombok.Data;

@Data
//사용자 vo
public class UserVo extends CommonVo{
    private Integer no;                 //사용자 rownum
    private Integer userNo;             //사용자 순번(시퀀스, pk)
    private String userId;              //사용자 아이디(pk)
    private String userPassword;        //사용자 비밀번호
    private String userName;            //사용자명
    private String userEmail;           //사용자 이메일
    private char useYn;                 //사용여부
    private String modDate;
}
