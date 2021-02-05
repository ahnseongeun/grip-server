package com.app.grip.src.user;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.models.PatchUserReq;
import com.app.grip.src.user.models.PatchUserRes;
import com.app.grip.src.user.models.PostUserReq;
import com.app.grip.src.user.models.PostUserRes;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    public PostUserRes createUserInfo(PostUserReq parameters) throws BaseException  {
        return null;
    }

    public PatchUserRes updateUserInfo(Integer userId, PatchUserReq parameters)  throws BaseException {
        return null;
    }

    public void deleteUserInfo(Integer userId) throws BaseException {
    }
}
