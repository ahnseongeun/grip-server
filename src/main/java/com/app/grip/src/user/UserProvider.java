package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.models.*;
import com.app.grip.utils.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@Service
public class UserProvider {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public UserProvider(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

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
     * @param String id
     * @return User
     * @throws BaseException
     * @comment 페이스북 id 회원조회
     * @Auther shine
     */
    @Transactional
    public User retrieveFacebookUserById(String id) throws BaseException {
        List<User> existsUserList;
        try {
            existsUserList = userRepository.findByIdAndSnsDiv(id, "F");
        } catch (Exception ignored) {
            throw new BaseException(FAILED_TO_GET_USER);
        }

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
     * @return GetUserRes
     * @throws BaseException
     * @comment 회원번호로 회원조회
     * @Auther shine
     */
    @Transactional
    public GetUserRes retrieveUser(Long userNo) throws BaseException {
        User user;

        try {
            user = userRepository.findById(userNo).orElse(null);
        } catch (Exception ignored) {
            throw new BaseException(FAILED_TO_GET_USER);
        }

        if (user == null || !user.getStatus().equals("Y")) {
            throw new BaseException(NOT_FOUND_USER);
        }

        return GetUserRes.builder()
                .userNo(user.getNo())
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .birthday(user.getBirthday())
                .profileImageURL(user.getProfileImageURL())
                .gender(user.getGender())
                .build();
    }

    /**
     * 페이스북 로그인
     * @param String userId
     * @return PostLoginFacebookRes
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public PostLoginFacebookRes login(String userId) throws BaseException {
        User user = null;

        try {
            user = retrieveFacebookUserById(userId);
        } catch (BaseException exception) {
            if (exception.getStatus() != NOT_FOUND_USER) {
                throw exception;
            }
        }

        if (user == null) {
            return new PostLoginFacebookRes(false, null, null);
        }

        return new PostLoginFacebookRes(true, user.getNo(), jwtService.createJwt(user.getNo()));
    }


    /**
     * 전체 회원 조회
     * @return
     * @throws BaseException
     */
    public List<GetUserRes> retrieveUserList() throws BaseException {

        List<User> userList;

        // DB에 접근해서 전체 회원 조회
        try{
            userList = userRepository.findByStatus("Y");
        }catch (Exception e){
            throw new BaseException(FAILED_TO_GET_USER);
        }


        return userList.stream()
                .map(user -> GetUserRes.builder()
                        .userNo(user.getNo())
                        .name(user.getName())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .birthday(user.getBirthday())
                        .profileImageURL(user.getProfileImageURL())
                        .gender(user.getGender())
                        .build())
                .collect(Collectors.toList());
    }

}