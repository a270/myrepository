package com.zzy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zzy.service.SmsService;
import com.zzy.util.SmsUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean send(String phoneNum, String templateCode, String code) {
        //连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", SmsUtil.AccessKey, SmsUtil.Secret);
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求,一般这里不用改动
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phoneNum); //手机号
        request.putQueryParameter("SignName",SmsUtil.Signature); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode",templateCode); //申请阿里云 模板code

        HashMap<String, Object> param = new HashMap<>();
        param.put("code", code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码数据，转换json数据传递,这里要用map

        try{
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();  //判断发送是否成功
        } catch (ClientException e){
            e.printStackTrace();
        }
        return false;
    }
}

