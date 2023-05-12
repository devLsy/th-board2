package study.thboard2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.thboard2.domain.vo.UserVo;
import study.thboard2.mapper.UserMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;

    /**
     * 사용자 목록 조회
     * @return
     * @throws Exception
     */
    public List<UserVo> getUserList() throws Exception{
        return userMapper.selectUserList();
    }

    /**
     * 사용자 정보 저장
     * @param  userVo
     * @throws Exception
     */
    public void regUser(UserVo userVo) {
        userMapper.insertUser(userVo);
    }

    /**
     * 아이디/비밀번호 확인
     * @param userId
     * @param userPassword
     * @return
     */
    public String login(String userId, String userPassword) {
        UserVo userInfo = userMapper.selectByUserId(userId);
        return (userInfo != null && userInfo.getUserPassword().equals(userPassword)) ? userInfo.getUserId() : "none";
    }

    /**
     * 사용자 상세 정보
     * @param userNo
     * @param userId
     * @return
     * @throws Exception
     */
    public UserVo getUserDetail(Integer userNo, String userId) throws Exception{
        return userMapper.selectUserDetail(userNo, userId);
    }

    /**
     * 사용자 정보 수정
     *
     * @param userVo
     * @throws Exception
     */
    public void modifyUser(UserVo userVo) throws Exception{
        userMapper.updateUser(userVo);
    }

    /**
     * 사용자 삭제
     *
     * @param userNo
     * @param userId
     * @throws Exception
     */
    public void removeUser(Integer userNo, String userId) {
        userMapper.delUser(userNo, userId);
    }
}
