package com.lf.hanzhijie.model.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;




/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hakuna
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUserPO{

    private static final long serialVersionUID = 1L;

    /**
     * 最后一次登录
     */
    public static String LAST_LOGIN = "MOBILE";

    private Long id;

    /**
     * 用户自增id
     */
    @TableId(value = "uuid", type = IdType.UUID)
    private String uuid;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 昵称
     */
    private String displayName;

    @TableField(exist = false)
    private String username;
    /**
     * 区号
     */
//    @NotBlank(message = "不能为空")
    private String regionCode;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 服务提供者id
     */
    private Long providerId;

    /**
     * tokenId
     */
    private transient String tokenId;

    /**
     * 验证码
     */
    @TableField(exist = false)
    private String captcha;

    /**
     * 登录类型
     */
    @TableField(exist = false)
    private String loginType;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 临时token
     */
    @TableField(exist = false)
    private String temporaryToken;

    /**
     * 医生头衔
     */
    private String title;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 状态 0已停用 1正常
     */
    private Integer isEnable;

    /**
     * 签名图片
     */
    private String signatureImg;

    /**
     * 最后一次登录
     */
    private String lastLogin;

    /**
     *
     */
    private Integer userStatus;
}