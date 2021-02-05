package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.models.GetUserRes;
import com.app.grip.src.user.models.GetUsersRes;
import com.app.grip.src.user.models.PostLoginReq;
import com.app.grip.src.user.models.PostLoginRes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoProvider {
    public List<GetUsersRes> retrieveUserInfoList(String word) throws BaseException {
        return null;
    }

    public GetUserRes retrieveUserInfo(Integer userId) throws BaseException {
        return null;
    }

    public PostLoginRes login(PostLoginReq parameters) throws BaseException {
        return null;
    }
}
