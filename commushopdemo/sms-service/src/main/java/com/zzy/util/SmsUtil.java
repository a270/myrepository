package com.zzy.util;

import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Component
@ConfigurationProperties(prefix = "aliyun")
public class SmsUtil implements InitializingBean {
    private String signature;
    private String templateCode;
    private String accessKey;
    private String secret;

    public static String Signature;
    public static String TemplateCode;
    public static String AccessKey;
    public static String Secret;

    @Override
    public void afterPropertiesSet() throws Exception {
        Signature = this.signature;
        TemplateCode = this.templateCode;
        AccessKey = this.accessKey;
        Secret = this.secret;
    }
}
