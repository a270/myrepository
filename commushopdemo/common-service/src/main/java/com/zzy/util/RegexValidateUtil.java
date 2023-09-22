package com.zzy.util;

import java.util.regex.Pattern;

/**
 * 使用正则表达式进行表单验证
 */
public class RegexValidateUtil {

    static String regex = "";

    public static boolean check(String str, String regex) {
        if ((str != null) && (!str.isEmpty())) {
            return Pattern.matches(regex, str);
        }
        return false;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String regex = "^\\w+((-\\w+)|(\\.\\w+))*@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        return check(email, regex);
    }

    /**
     * 验证手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        String regex = "^((19[0-9])|(17[0-9])|(13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        return check(mobile, regex);
    }

    /**
     * 验证QQ号码
     *
     * @param QQ
     * @return
     */
    public static boolean checkQQ(String QQ) {
        String regex = "^[1-9][0-9]{4,}$";
        return check(QQ, regex);
    }
}