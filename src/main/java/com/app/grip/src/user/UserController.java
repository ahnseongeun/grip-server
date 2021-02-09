package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.user.models.*;
import com.app.grip.utils.JwtService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.app.grip.config.BaseResponseStatus.*;
import static com.app.grip.utils.ApiExamMemberProfile.getNaverTokenResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserProvider userProvider;
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserProvider userProvider, UserService userService, JwtService jwtService) {
        this.userProvider = userProvider;
        this.userService = userService;
        this.jwtService = jwtService;
    }

//    /**
//     * 회원 전체 조회 API
//     * [GET] /users
//     * 회원 닉네임 검색 조회 API
//     * [GET] /users?word=
//     * @return BaseResponse<List<GetUsersRes>>
//     */
//    @ResponseBody
//    @GetMapping("") // (GET) 127.0.0.1:9000/users
//    public BaseResponse<List<GetUsersRes>> getUsers(@RequestParam(required = false) String word) {
//        try {
//            List<GetUsersRes> getUsersResList = userInfoProvider.retrieveUserInfoList(word);
//            if (word == null) {
//                return new BaseResponse<>(SUCCESS_READ_USERS, getUsersResList);
//            } else {
//                return new BaseResponse<>(SUCCESS_READ_SEARCH_USERS, getUsersResList);
//            }
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }

//    /**
//     * 회원 조회 API
//     * [GET] /users/:userId
//     * @PathVariable userId
//     * @return BaseResponse<GetUserRes>
//     */
//    @ResponseBody
//    @GetMapping("/{userId}")
//    public BaseResponse<GetUserRes> getUser(@PathVariable Integer userId) {
//        if (userId == null || userId <= 0) {
//            return new BaseResponse<>(EMPTY_USERID);
//        }
//
//        try {
//            GetUserRes getUserRes = userInfoProvider.retrieveUserInfo(userId);
//            return new BaseResponse<>(SUCCESS_READ_USER, getUserRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }

//    }
    /**
     * 네이버 회원가입 및 로그인 API
     * [POST] /api/users/naver
     * @RequestBody PostUserReq
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PostMapping("/naver")
    public BaseResponse<PostUserRes> postUsersByNaver(
            @RequestHeader(value = "token") String token) {

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
            PostUserRes postUserRes = userService.createUserInfo(responseBody);

            if (postUserRes.getResponse().equals("login")) {
                return new BaseResponse<>(SUCCESS_LOGIN, postUserRes);
            }
            return new BaseResponse<>(SUCCESS_POST_USER, postUserRes);

        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 페이스북 회원가입 API
     * [POST] /api/users/facebook
     * @RequestBody PostUserReq
     * @return BaseResponse<PostUserRes>
     */
    @ResponseBody
    @PostMapping("/facebook")
    public BaseResponse<PostUserRes> postUsersByFacebook(@RequestBody PostUserReq parameters) {
        if (parameters.getNickname() == null || parameters.getNickname().length() == 0) {
            return new BaseResponse<>(EMPTY_NICKNAME);
        }

        try {
            PostUserRes postUserRes = userService.createUserFacebook(parameters);
            return new BaseResponse<>(SUCCESS, postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * JWT 검증 API(토큰있을때)
     * [GET] /users/jwt
     * @return BaseResponse<Void>
     */
    @GetMapping("/jwt")
    public BaseResponse<Void> jwt() {
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
     * @return BaseResponse<PostLoginRes>
     */
    @ResponseBody
    @PostMapping("/login/facebook")
    public BaseResponse<PostLoginRes> postLoginByFacebook(@RequestHeader(value = "token") String token) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        //String token = "773022300256919%7C0f91a67de5f802547d97ee9f25484361";        // 유효한 토큰
        //String token = "773022300256919%7C0f91a67de5f802547d97ee9f25484361asd";     // 유요하지 않은 토큰

        try {
            URL url = new URL("https://graph.facebook.com/debug_token?input_token=" + token
                    + "&access_token=" + "773022300256919|IcoV0PpB3NqIKrOBK6xDvawowuc");        // access_token => app_access_token
                                                                                                // 개발자가 프로젝트 설정에서 따로 app-secret, id 같은 것들을 변경하지않는한 영구히 유효
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            // 클라에서 전달받은 결과값 json으로 받기
            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400:: 해당 명령을 실행할 수 없음");
            } else if (responseCode == 401) {
                System.out.println("401:: X-Auth-Token Header가 잘못됨");
            } else if (responseCode == 500) {
                System.out.println("500:: 서버 에러, 문의 필요");
            } else {                // 성공 후 응답 JSON 데이터받기
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }

        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (JSONException exception) {
            System.out.println("not JSON Format response");
            exception.printStackTrace();
        }

        // 전달받은 값이 유효한지 검사
        String appId = "";
        try {
            appId = responseJson.getJSONObject("data").getString("app_id");
        } catch (Exception exception) {
            appId = "fail";
            return new BaseResponse<>(FAILED_TO_FACEBOOKTOCKEN);
        }

        // 만약 회원 테이블에 있다면 회원가입으로 이동, 없다면 로그인
        try {
            PostLoginRes postLoginRes = userProvider.login(appId);
            return new BaseResponse<>(SUCCESS, postLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }



//    /**
//     * 회원 정보 수정 API
//     * [PATCH] /users/:userId
//     * @PathVariable userId
//     * @RequestBody PatchUserReq
//     * @return BaseResponse<PatchUserRes>
//     */
//    @ResponseBody
//    @PatchMapping("/{userId}")
//    public BaseResponse<PatchUserRes> patchUsers(@PathVariable Integer userId, @RequestBody PatchUserReq parameters) {
//        if (userId == null || userId <= 0) {
//            return new BaseResponse<>(EMPTY_USERID);
//        }
//
//        if (!parameters.getPassword().equals(parameters.getConfirmPassword())) {
//            return new BaseResponse<>(DO_NOT_MATCH_PASSWORD);
//        }
//
//        try {
//            return new BaseResponse<>(SUCCESS_PATCH_USER, userInfoService.updateUserInfo(userId, parameters));
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 로그인 API
//     * [POST] /users/login
//     * @RequestBody PostLoginReq
//     * @return BaseResponse<PostLoginRes>
//     */
//    @PostMapping("/login")
//    public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq parameters) {
//        // 1. Body Parameter Validation
//        if (parameters.getEmail() == null || parameters.getEmail().length() == 0) {
//            return new BaseResponse<>(EMPTY_EMAIL);
//        } else if (!isRegexEmail(parameters.getEmail())) {
//            return new BaseResponse<>(INVALID_EMAIL);
//        } else if (parameters.getPassword() == null || parameters.getPassword().length() == 0) {
//            return new BaseResponse<>(EMPTY_PASSWORD);
//        }
//
//        // 2. Login
//        try {
//            PostLoginRes postLoginRes = userInfoProvider.login(parameters);
//            return new BaseResponse<>(SUCCESS_LOGIN, postLoginRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
//
//    /**
//     * 회원 탈퇴 API
//     * [DELETE] /users/:userId
//     * @PathVariable userId
//     * @return BaseResponse<Void>
//     */
//    @DeleteMapping("/{userId}")
//    public BaseResponse<Void> deleteUsers(@PathVariable Integer userId) {
//        if (userId == null || userId <= 0) {
//            return new BaseResponse<>(EMPTY_USERID);
//        }
//
//        try {
//            userInfoService.deleteUserInfo(userId);
//            return new BaseResponse<>(SUCCESS_DELETE_USER);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }

}