package com.lf.hanzhijie.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserReq {
    private String phoneNum;
    /**
     * 邮箱
     */
    private String email;
    private String loginType;
    private String captcha;
    private String password;

}
