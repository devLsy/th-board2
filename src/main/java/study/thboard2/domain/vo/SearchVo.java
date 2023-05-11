package study.thboard2.domain.vo;

import lombok.Data;

@Data
public class SearchVo {

    //검색 타입
    private String type;
    //검색 키워드
    private String keyword;
}
