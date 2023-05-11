package study.thboard2.domain.vo;

import lombok.Data;

@Data
//첨부파일 관련 vo
public class FileVo extends CommonVo{

    private Integer fileNo;             //파일순번(pk)
    private Integer boardNo;            //게시글순번(fk)
    private String fileOrgName;         //원본파일명
    private String filePath;            //파일경로
    private int fileSize;           //파일용량
    private String delYn;               //삭제여부
    
}
