package com.app.grip.utils;

import com.app.grip.config.BaseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.app.grip.config.BaseResponseStatus.*;

@Service
public class SNSLogin {
    /**
     * 페이스북 토큰 검증 API
     * @Param String token
     * @return JSONObject
     * @throws BaseException
     * @Auther shine
     */
    public String FaceBookAuthentication(String token) throws BaseException {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL("https://graph.facebook.com/debug_token?input_token=" + token
                    + "&access_token=" + "773022300256919|IcoV0PpB3NqIKrOBK6xDvawowuc");        // access_token => app_access_token
            // 개발자가 프로젝트 설정에서 따로 app-secret, id 같은 것들을 변경하지않는한 영구히 유효
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            // 클라에서 전달받은 결과값 json으로 받기
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            } else if (responseCode == 400) {
                throw new BaseException(FACEBOOK_CONNECTION_400);
            } else if (responseCode == 500) {
                throw new BaseException(FACEBOOK_CONNECTION_500);
            } else {
                throw new BaseException(FACEBOOK_CONNECTION);
            }

        } catch (MalformedURLException exception) {
            throw new BaseException(FACEBOOK_CONNECTION_URL);
        } catch (IOException exception) {
            throw new BaseException(FACEBOOK_CONNECTION_IO);
        } catch (JSONException exception) {
            throw new BaseException(FACEBOOK_CONNECTION_NOT_JSON_RESPONSE);
        }

        String userId = "";
        try {
            userId = responseJson.getJSONObject("data").getString("user_id");
        } catch (Exception exception) {
            throw new BaseException(FACEBOOK_CONNECTION_INVALID_TOKEN);
        }

        return userId;
    }
}