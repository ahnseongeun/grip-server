package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.user.models.*;
import com.app.grip.utils.SNSLogin;
import com.app.grip.utils.ValidationRegex;
import com.app.grip.utils.jwt.JwtService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.grip.config.BaseResponseStatus.*;
import static com.app.grip.utils.ApiExamMemberProfile.getNaverTokenResponse;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;
    private final ValidationRegex validationRegex;
    private final SNSLogin snsLogin;

    @Autowired
    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService,
                          ValidationRegex validationRegex, SNSLogin snsLogin) {
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
        this.validationRegex = validationRegex;
        this.snsLogin = snsLogin;
    }

    /**
     * 회원 전체 조회 API
     * [GET] /users
     * 회원 닉네임 검색 조회 API
     * [GET] /users?word=
     * @return BaseResponse<List<GetUsersRes>>
     */
    @ResponseBody
    @GetMapping("/admin/users")
    @ApiOperation(value = "회원 전체 조회", notes = "회원 전체 조회")
    public BaseResponse<List<GetUserRes>> getUsers() {
        try {
            List<GetUserRes> getUsersResList = userProvider.retrieveUserList();
            return new BaseResponse<>(SUCCESS, getUsersResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원 조회 API
     * [GET] /users/:userId
     * @PathVariable userId
     * @return BaseResponse<GetUserRes>
     */
    @ResponseBody
    @GetMapping("/users")
    @ApiOperation(value = "내 프로필 조회", notes = "내 프로필 조회")
    public BaseResponse<GetUserRes> getUser(
            @RequestHeader(value = "jwt") String jwt) throws BaseException {
        Long userNo = jwtService.getUserNo();

        if (userNo == null || userNo <= 0) {
            return new BaseResponse<>(EMPTY_USERID);
        }

        try {
            GetUserRes getUserRes = userProvider.retrieveUser(userNo);
            return new BaseResponse<>(SUCCESS, getUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /**
     * 네이버 회원가입 및 로그인 API
     * [POST] /api/users/naver
     * @RequestBody PostUserReq
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PostMapping("/users/naver")
    @ApiOperation(value = "네이버 로그인 및 회원가입",
            notes = "네이버 로그인 및 회원가입\n"+"로그인은 login Y, 회원가입은 login N로 구분")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", value = "Y일경우 로그인, N일 경우 회원 가입", required = false, dataType = "string", paramType = "query", defaultValue="Y")
    })
    public BaseResponse<PostUserRes> postUsersByNaver(
            @RequestHeader(value = "token") String token,
            @RequestParam(value = "login",required = false,defaultValue = "Y") String login) {

        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = getNaverTokenResponse(apiURL, requestHeaders);
//        System.out.println(responseBody);
//        return null;
//        // 1. Body Parameter Validation
//        if (parameters.getEmail() == null || parameters.getEmail().length() == 0) {
//            return new BaseResponse<>(EMPTY_EMAIL);
//        }
//        if (!isRegexEmail(parameters.getEmail())){
//            return new BaseResponse<>(INVALID_EMAIL);
//        }
//        if (parameters.getNickname() == null || parameters.getNickname().length() == 0) {
//            return new BaseResponse<>(EMPTY_NICKNAME);
//        }

        // 2. Post UserInfo
        try {
            PostUserRes postUserRes = userService.createUserInfo(responseBody,login);

            if (postUserRes.getResponse().equals("login")) {
                return new BaseResponse<>(SUCCESS_LOGIN, postUserRes);
            }
            return new BaseResponse<>(SUCCESS_POST_USER, postUserRes);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 관리자용 로그인 및 JWT 발급
     * [POST] /api/users/naver
     * @RequestBody PostUserReq
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PostMapping("/admin/users/{userId}")
    @ApiOperation(value = "관리자용 로그인 및 JWT 발급(서버측 사용)", notes = "관리자용 로그인 및 JWT 발급")
    public BaseResponse<PostUserFacebookRes> GetUsersByAdmin(@PathVariable Long userId) {
        try {
            PostUserFacebookRes postUserFacebookRes = userService.GetUserInfo(userId);

            return new BaseResponse<>(SUCCESS, postUserFacebookRes);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 페이스북 회원가입 API
     * [POST] /api/users/facebook
     * @RequestBody PostUserFacebookReq
     * @return BaseResponse<PostUserFacebookRes>
     * @Auther shine
     */
    @ApiOperation(value = "페이스북 회원가입", notes = "페이스북 회원가입")
    @ResponseBody
    @PostMapping("/users/facebook")
    public BaseResponse<PostUserFacebookRes> postUsersByFacebook(@RequestHeader(value = "token") String token, @RequestBody(required = false) PostUserFacebookReq parameters) {
        String userId = "";
        try {
            userId = snsLogin.FaceBookAuthentication(token);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(parameters.getNickname() == null || parameters.getNickname().length() == 0) {
            return new BaseResponse<>(EMPTY_NICKNAME);
        }
        if(parameters.getName() == null || parameters.getName().length() == 0) {
            return new BaseResponse<>(EMPTY_NAME);
        }

        if(parameters.getPhoneNumber() != null) {
            if(!validationRegex.isRegexPhoneNumber(parameters.getPhoneNumber())) {
                return new BaseResponse<>(INVALID_PHONENUMBER);
            }
        }
        if(parameters.getEmail() != null) {
            if(!validationRegex.isRegexEmail(parameters.getEmail())) {
                return new BaseResponse<>(INVALID_EMAIL);
            }
        }

        try {
            PostUserFacebookRes postUserFacebookRes = userService.createUserFacebook(parameters, userId);
            return new BaseResponse<>(SUCCESS, postUserFacebookRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * JWT 검증 API(토큰있을때)
     * [GET] /api/users/jwt
     * @RequestHeader jwt
     * @return BaseResponse<Void>
     * @Auther shine
     */
    @ApiOperation(value = "JWT 검증 (자동로그인)", notes = "JWT 검증 (자동로그인)")
    @GetMapping("/users/jwt")
    public BaseResponse<Void> jwt(@RequestHeader(value = "jwt") String jwt) {
        try {
            Long userNo = jwtService.getUserNo();
            userProvider.retrieveUser(userNo);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 페이스북 로그인 API(토큰없을때)
     * [POST] /api/users/login/facebook
     * @RequestHeader token
     * @return BaseResponse<PostLoginFacebookRes>
     * @Auther shine
     */
    @ApiOperation(value = "페이스북 로그인", notes = "페이스북 로그인\nisMember false 회원가입 필요, isMember true 로그인 성공")
    @ResponseBody
    @PostMapping("/users/login/facebook")
    public BaseResponse<PostLoginFacebookRes> postLoginByFacebook(@RequestHeader(value = "token") String token) {
        String userId = "";
        try {
            userId = snsLogin.FaceBookAuthentication(token);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        // 만약 회원 테이블에 있다면 회원가입으로 이동, 없다면 로그인
        try {
            PostLoginFacebookRes postLoginRes = userProvider.login(userId);
            return new BaseResponse<>(SUCCESS, postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 회원 정보 수정 API
     * [PATCH] /users/
     * @RequestBody PatchUserReq
     * @return BaseResponse<PatchUserRes>
     */
    /*

     */
    @ResponseBody
    @PatchMapping("/users")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageDelete", value = "Y일경우 image 삭제, N일 경우 기존 이미지 유지", required = false, dataType = "string", paramType = "query", defaultValue="N")
    })
    public BaseResponse<PatchUserRes> patchUsers(
            @RequestHeader(value = "jwt") String jwt,
            @RequestParam(value = "profileImage",required = false) MultipartFile profileImage,
            @RequestParam(value = "nickname",required = false) String nickname,
            @RequestParam(value = "phoneNumber",required = false) String phoneNumber,
            @RequestParam(value = "birthday",required = false) String birthday,
            @RequestParam(value = "imageDelete",required = false,defaultValue = "N") String imageDelete) {

        try {
            Long userNo = jwtService.getUserNo();

            if (userNo == null || userNo <= 0) {
                return new BaseResponse<>(EMPTY_USERID);
            }

            PatchUserRes patchUserRes = userService.updateUser(userNo, profileImage,nickname,phoneNumber,birthday,imageDelete);

            return new BaseResponse<>(SUCCESS, patchUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원 탈퇴 API
     * [DELETE] /users
     * @PathVariable userId
     * @return BaseResponse<Void>
     */
    @ResponseBody
    @PatchMapping("/users/delete")
    @ApiOperation(value = "회원 정보 삭제", notes = "회원 정보 삭제")
    public BaseResponse<Void> deleteUsers(
            @RequestHeader(value = "jwt") String jwt) {

        try {
            Long userNo = jwtService.getUserNo();

            if (userNo == null || userNo <= 0) {
                return new BaseResponse<>(EMPTY_USERID);
            }

            userService.deleteUser(userNo);
            return new BaseResponse<>(SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 테스트용 jwt 생성, 나중에 삭제할거
     * [post] /api/jwt
     */
    @PostMapping("/jwt/{id}")
    public String postJWT(@PathVariable Long id) {
        return jwtService.createJwt(id);
    }

}