package com.zzy.controller;

import com.aliyun.credentials.utils.StringUtils;
import com.zzy.exception.ShopException;
import com.zzy.result.ResponseEnum;
import com.zzy.service.SmsService;
import com.zzy.util.RandomUtil;
import com.zzy.util.RegexValidateUtil;
import com.zzy.util.ResultVOUtil;
import com.zzy.util.SmsUtil;
import com.zzy.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send/{mobile}")
    public ResultVO code(@PathVariable("mobile") String phone){

        boolean b = RegexValidateUtil.checkMobile(phone);//使用正则校验手机号格式
        if(!b){
            throw new ShopException(ResponseEnum.MOBILE_ERROR.getMsg());
        }

        //如果redis缓存中存在手机号的验证码，说明验证码还未过期，可继续使用
        String code = (String) redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return ResultVOUtil.fail("验证码未过期，可以继续使用");
        }

        code = RandomUtil.getSixBitRandom();
        boolean send = this.smsService.send(phone, SmsUtil.TemplateCode , code);
        if(send){
            //把code存入Redis
            this.redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            //this表示用的自己的成员变量
            return ResultVOUtil.success("短信发送成功");
        }
        return ResultVOUtil.fail(null);

    }

}

