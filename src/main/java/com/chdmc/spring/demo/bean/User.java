package com.chdmc.spring.demo.bean;

import java.io.Serializable;

/**
 * 用户信息
 */
public class User implements Serializable {

    public  enum USER_TYPE {
        NORMAL("normal"),SUPER_ADMIN("superadmin"),ADMIN("admin");
        private final String type;
        USER_TYPE(String type) {
            this.type = type;
        }

        public static USER_TYPE getUserTyep(String userType) {
            switch (userType) {
                case "superadmin":
                    return SUPER_ADMIN;
                case "admin":
                    return ADMIN;
                default:
                case "normal":
                    return NORMAL;
            }
        }

        @Override
        public String toString() {
            return type;
        }
    }

    private static final long serialVersionUID = -3258839839160856613L;
    private String userName;
    private String passWord;
    private String icon;
    /**
     * 用户类型
     */
    private USER_TYPE userType;

    /**
     * 手机号
     */
    private String phoneNumber;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public USER_TYPE getUserType() {
        return userType;
    }

    public void setUserType(USER_TYPE userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
