package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.models.*;
import com.app.grip.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_USER;
import static com.app.grip.config.BaseResponseStatus.NOT_FOUND_USER;

@Service
public class UserProvider {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public UserProvider(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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


    /**
     * 회원 조회
     * @param id
     * @return User
     * @throws BaseException
     * @comment 페이스북 id 회원조회
     */
    @Transactional
    public User retrieveFacebookUserById(String id) throws BaseException {
        List<User> existsUserList;
        try {
            existsUserList = userRepository.findByIdAndSnsDiv(id, "F");
        } catch (Exception ignored) {
            throw new BaseException(FAILED_TO_GET_USER);
        }

        // 존재하는 UserInfo가 있는지 확인
        User user;
        if (existsUserList != null && existsUserList.size() > 0) {
            user = existsUserList.get(0);
        } else {
            throw new BaseException(NOT_FOUND_USER);
        }

        // UserInfo를 return
        return user;
    }

    /**
     * 회원 조회
     * @param userNo
     * @return User
     * @throws BaseException
     * @comment 회원번호로 회원조회
     */
    @Transactional
    public User retrieveUser(Long userNo) throws BaseException {
        User user;

        try {
            user = userRepository.findById(userNo).orElse(null);
        } catch (Exception ignored) {
            throw new BaseException(FAILED_TO_GET_USER);
        }

        if (user == null || !user.getStatus().equals("Y")) {
            throw new BaseException(NOT_FOUND_USER);
        }

        return user;
    }

    /**
     * 페이스북 로그인
     * @param appId
     * @return PostLoginRes
     * @throws BaseException
     */
    @Transactional
    public PostLoginFacebookRes login(String appId) throws BaseException {
        User user = null;

        try {
            user = retrieveFacebookUserById(appId);
        } catch (BaseException exception) {
            if (exception.getStatus() != NOT_FOUND_USER) {
                throw exception;
            }
        }

        if (user == null) {
            return new PostLoginFacebookRes("true", null, null, appId);
        }

        String jwt = jwtService.createJwt(user.getNo());
        return new PostLoginFacebookRes("false", user.getNo(), jwt, null);
    }






}
