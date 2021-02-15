package com.app.grip.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    // 1000 : 요청 성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    SUCCESS_POST_USER(true, 1012, "회원가입에 성공하였습니다."),
    SUCCESS_LOGIN(true, 1013, "로그인에 성공하였습니다."),

    // 2000 : Request 오류
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_USERID(false, 2001, "유저 아이디 값을 확인해주세요."),
    EMPTY_JWT(false, 2010, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2011, "유효하지 않은 JWT입니다."),
    EMPTY_EMAIL(false, 2020, "이메일을 입력해주세요."),
    INVALID_EMAIL(false, 2021, "이메일 형식을 확인해주세요."),
    EMPTY_NICKNAME(false, 2040, "닉네임을 입력해주세요."),
    // shine
    EMPTY_FACEBOOK_USERID(false, 2100, "페이스북 아이디가 누락되었습니다."),
    EMPTY_NAME(false, 2101, "이름을 입력해주세요."),
    EMPTY_INTRODUCTION(false, 2102, "소개말을 입력해주세요."),
    EMPTY_STAR(false, 2103, "별점을 입력해주세요."),
    EMPTY_CONTENT(false, 2104, "내용을 입력해주세요."),
    EMPTY_DISCOUNT(false, 2105, "할인금액을 입력해주세요."),
    EMPTY_MINIMUMPRICE(false, 2106, "최저 사용금액을 입력해주세요."),
    EMPTY_EFFECTIVEDATE(false, 2107, "쿠폰 유효일자를 입력해주세요."),
    EMPTY_PRICE(false, 2108, "가격을 입력해주세요."),
    EMPTY_CATEGORY(false, 2109, "카테고리를 입력해주세요."),
    INVALID_PHONENUMBER(false, 2112, "휴대전화번호 형식을 확인해주세요."),
    INVALID_EFFECTIVEDATE(false, 2113, "쿠폰 유효일자 형식을 확인해주세요."),



    // divi
    INVALID_TOKEN(false, 2500, "토큰이 유효하지 않습니다."),

    // 3000 : Response 오류
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),
    NOT_FOUND_USER(false, 3010, "존재하지 않는 회원입니다."),
    DUPLICATED_USER(false, 3011, "이미 존재하는 회원입니다."),
    FAILED_TO_GET_USER(false, 3012, "회원 정보 조회에 실패하였습니다."),
    FAILED_TO_POST_USER(false, 3013, "회원가입에 실패하였습니다."),
    FAILED_TO_LOGIN(false, 3014, "로그인에 실패하였습니다."),
    FAILED_TO_DELETE_USER(false, 3015, "회원 탈퇴에 실패하였습니다."),
    FAILED_TO_PATCH_USER(false, 3016, "개인정보 수정에 실패하였습니다."),
    // shine
    FACEBOOK_CONNECTION_INVALID_TOKEN(false, 3100,"유효하지 않은 페이스북 토큰입니다."),
    FACEBOOK_CONNECTION(false, 3101,"페이스북 연결 에러가 발생했습니다."),
    FACEBOOK_CONNECTION_400(false, 3102,"페이스북에 잘못된 요청을 보냈습니다."),
    FACEBOOK_CONNECTION_500(false, 3103,"페이스북 서버 에러가 발생했습니다."),
    FACEBOOK_CONNECTION_URL(false, 3104,"잘못된 URL입니다."),
    FACEBOOK_CONNECTION_IO(false, 3105,"페이스북과 통신 중 IO 에러가 발생했습니다."),
    FACEBOOK_CONNECTION_NOT_JSON_RESPONSE(false, 3106,"페이스북의 response가 JSON Format response가 아닙니다."),
    FAILED_TO_GET_STORE(false, 3107, "상점 검색에 실패하였습니다."),
    FAILED_TO_POST_STORE(false, 3108, "상점 등록에 실패하였습니다."),
    FAILED_TO_GET_REVIEW(false, 3109, "리뷰 검색에 실패하였습니다."),
    FAILED_TO_POST_REVIEW(false, 3110, "리뷰 등록에 실패하였습니다."),
    FAILED_TO_GET_COUPON(false, 3111, "쿠폰 검색에 실패하였습니다."),
    FAILED_TO_POST_COUPON(false, 3112, "쿠폰 등록에 실패하였습니다."),
    FAILED_TO_GET_PRODUCT(false, 3113, "상품 검색에 실패하였습니다."),
    FAILED_TO_POST_PRODUCT(false, 3114, "상품 등록에 실패하였습니다."),
    FAILED_TO_PATCH_PRODUCT(false, 3114, "상품 수정에 실패하였습니다."),
    FAILED_TO_GET_PRODUCTCATEGORY(false, 3115, "상품 카테고리 검색에 실패하였습니다."),
    FAILED_TO_POST_PRODUCTCATEGORY(false, 3116, "상품 카테고리 등록에 실패하였습니다."),
    NOT_FOUND_PRODUCT(false, 3117, "존재하지 않는 상품입니다."),
    NOT_FOUND_REVIEW(false, 3117, "존재하지 않는 리뷰입니다."),
    ALREADY_COMPLETED(false, 3118, "이미 판매종료된 상품입니다."),
    ALREADY_DELETE_PRODUCT(false, 3119, "이미 삭제된 상품입니다."),
    ALREADY_DELETE_REVIEW(false, 3119, "이미 삭제된 리뷰입니다."),
    DO_NOT_AUTH_USER(false, 3120, "작성 권한이 없는 사용자입니다."),
    DUPLICATED_STORE(false, 3121, "해당 사용자의 상점은 이미 존재합니다."),

    // divi
    FAILED_TO_GET_VIDEO_CATEGORY(false, 3500, "영상 카테고리 조회에 실패하였습니다."),
    FAILED_TO_POST_IMAGE(false, 3501, "이미지 등록에 실패하였습니다."),
    NOT_FOUND_ADVERTISEMENT(false, 3502, "광고 조회 실패하였습니다."),
    FAILED_TO_UPLOAD_IMAGE(false, 3503, "이미지 등록에 실패하였습니다."),
    FAILED_TO_GET_VIDEO(false, 3504, "영상 조회에 실패하였습니다."),
    END_TO_VIDEO(false, 3505, "라이브가 종료 되었습니다."),
    NOT_START_VIDEO(false, 3506, "라이브가 아직 시작하지 않았습니다."),
    FAILED_TO_POST_VIDEO_LIKE(false, 3507, "좋아요 영구저장에 실패했습니다."),
    FAILED_TO_GET_VIDEO_LIKE(false,3508,"좋아요 조회에 실패했습니다."),


    // 4000 : Database 오류
    DATABASE_ERROR(false, 4000, "데이터베이스 관련한 오류가 발생하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
