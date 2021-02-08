package com.app.grip;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ObjectTest {
    public static void main(String[] args) {
        String token = "{\"resultcode\":\"00\",\"message\":\"success\",\"response\":{\"id\":\"27551400\",\"nickname\":\"\\ud558\\uc5ec\\uc2dc\",\"profile_image\":\"https:\\/\\/phinf.pstatic.net\\/contact\\/profile\\/blog\\/30\\/65\\/dudtls153.jpg\",\"age\":\"20-29\",\"gender\":\"M\",\"email\":\"dudtls153@naver.com\",\"mobile\":\"010-2234-5619\",\"mobile_e164\":\"+821022345619\",\"name\":\"\\ud55c\\uc601\\uc2e0\",\"birthday\":\"02-26\",\"birthyear\":\"1996\"}}"; // 네이버 로그인 접근 토큰;

        try {
            JSONObject json = new JSONObject(token);
            System.out.println(json.getJSONObject("response").getString("nickname"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
