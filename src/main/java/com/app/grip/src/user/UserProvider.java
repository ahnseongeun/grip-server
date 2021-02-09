package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_USER;
import static com.app.grip.config.BaseResponseStatus.NOT_FOUND_USER;

@Service
public class UserProvider {

    private final UserRepository userRepository;

    @Autowired
    public UserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public List<GetUsersRes> retrieveUserInfoList(String word) throws BaseException {
//        return null;
//    }
//
//    public GetUserRes retrieveUserInfo(Integer userId) throws BaseException {
//        return null;
//    }
//
//    public PostLoginRes login(PostLoginReq parameters) throws BaseException {
//        return null;
//    }

    /**
     * email로 중복된 회원 조회
     * @param phoneNumber
     * @return
     */
    public User retrieveUserByPhoneNumber(String phoneNumber) throws BaseException {

        List<User> existsUserList;

        // DB에 접근해서 email로 회원 정보 조회
        try{
            existsUserList = userRepository.findByStatusAndPhoneNumberIsContaining("Y",phoneNumber);
        }catch (Exception e){
            throw new BaseException(FAILED_TO_GET_USER);
        }

        // userList에 중복된 회원이 있는지 검사
        User user;
        if (existsUserList != null && existsUserList.size() > 0) {
            user = existsUserList.get(0);
        } else {
            throw new BaseException(NOT_FOUND_USER);
        }

        return user;
    }
}
