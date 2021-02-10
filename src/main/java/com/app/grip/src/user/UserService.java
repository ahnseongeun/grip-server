package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.models.*;
import com.app.grip.utils.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.*;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserProvider userProvider;
    private final JwtService jwtService;

    @Autowired
    public UserService(UserRepository userRepository, UserProvider userProvider, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userProvider = userProvider;
        this.jwtService = jwtService;
    }

    public PostUserRes createUserInfo(String parameters,boolean login) throws BaseException {

        User existsUser = null;

        String name = null;
        String nickName = null;
        String birthday = null;
        String birthyear = null;
        String email = null;
        String phoneNumber = null;
        String gender = null;
        String id = null;
        String imageStatus = "Y";
        String profile_image = null;

        JSONObject json = new JSONObject(parameters);
        try {
            name = json.getJSONObject("response").getString("name");
            nickName = json.getJSONObject("response").getString("nickname");
            birthyear = json.getJSONObject("response").getString("birthyear");
            birthday = json.getJSONObject("response").getString("birthday");
            email = json.getJSONObject("response").getString("email");
            phoneNumber = json.getJSONObject("response").getString("mobile");
            gender = json.getJSONObject("response").getString("gender");
            id = json.getJSONObject("response").getString("id");
            profile_image = json.getJSONObject("response").getString("profile_image");
        }catch (JSONException jsonException){
            throw new BaseException(INVALID_TOKEN);
        }


        try {
            // 1-1. 이미 존재하는 회원이 있는지 조회
            existsUser = userProvider.retrieveUserByPhoneNumber(phoneNumber);
        } catch (BaseException exception) {
            // 1-2. 이미 존재하는 회원이 없다면 그대로 진행
            if (exception.getStatus() != NOT_FOUND_USER) {
                throw exception;
            }
        }


        // 1-3. 이미 존재하는 회원이 있다면 return DUPLICATED_USER
        if (existsUser != null && !login) {
            throw new BaseException(DUPLICATED_USER);
        }


        // 1-4. 로그인 구현
        if (existsUser != null) {
            log.info("login");
            return PostUserRes.builder()
                    .userNo(existsUser.getNo())
                    .jwt(jwtService.createJwt(existsUser.getNo()))
                    .response("login")
                    .build();
        }

        User newUser = User.builder()
                .name(name)
                .nickname(nickName)
                .birthday(birthyear+"-"+birthday)
                .email(email)
                .gender(gender)
                .id(id)
                .imageStatus("Y")
                .phoneNumber(phoneNumber)
                .profileImageURL(profile_image)
                .role(1)
                .snsDiv("N")
                .build();

        // 3. 유저 정보 저장
        try {
            newUser = userRepository.save(newUser);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_USER);
        }

        return PostUserRes.builder()
                .userNo(newUser.getNo())
                .jwt(jwtService.createJwt(newUser.getNo()))
                .response("registry")
                .build();
    }

    /**
     * 페이스북 회원가입
     * @param postUserReq
     * @return PostUserFacebookRes
     * @throws BaseException
     * @Auther shine
     */
    public PostUserFacebookRes createUserFacebook(PostUserFacebookReq postUserReq) throws BaseException {
        User newUser = User.builder()
                .name(postUserReq.getName())
                .nickname(postUserReq.getNickname())
                .birthday(postUserReq.getBirthday())
                .email(postUserReq.getEmail())
                .gender(postUserReq.getGender())
                .id(postUserReq.getUserId())
                .imageStatus("Y")
                .phoneNumber(postUserReq.getPhoneNumber())
                .profileImageURL(postUserReq.getProfileImage())
                .role(1)
                .snsDiv("F")
                .build();

        try {
            newUser = userRepository.save(newUser);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(FAILED_TO_POST_USER);
        }

        String jwt = jwtService.createJwt(newUser.getNo());
        return new PostUserFacebookRes(newUser.getNo(), jwt);
    }


//    public PatchUserRes updateUserInfo(Integer userId, PatchUserReq parameters)  throws BaseException {
//        return null;
//    }
//
//    public void deleteUserInfo(Integer userId) throws BaseException {
//    }
}
