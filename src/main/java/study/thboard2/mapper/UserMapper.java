package study.thboard2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import study.thboard2.domain.vo.UserVo;

import java.util.List;

@Repository @Mapper
public interface UserMapper {

    /* 사용자 목록 조회 */
    List<UserVo> selectUserList();

    int selectIdCnt(@Param("userId") String userId);

    /* 사용자 정보 저장 */
    int insertUser(UserVo userVo);

    /* 사용자 정보 확인(로그인 시 활용) */
    UserVo selectByUserId(@Param("userId") String userId);

    /* 사용자 상세 정보 */
    UserVo selectUserDetail(@Param("userId") String userId);

    /* 사용자 정보 수정 */
    void updateUser(UserVo userVo);

    /* 사용자 삭제 */
    void delUser(@Param(("userNo")) Integer userNo, @Param("userId") String userId);
}
