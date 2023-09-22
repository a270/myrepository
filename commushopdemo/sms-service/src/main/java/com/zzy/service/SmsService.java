package com.zzy.service;

public interface SmsService {
    //手机号、模板代码、验证码
    public boolean send(String phoneNum, String templateCode, String code);
}

