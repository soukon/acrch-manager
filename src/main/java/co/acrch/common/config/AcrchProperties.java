package co.acrch.common.config;

import co.acrch.common.domain.ValidateCodeProperties;
import co.acrch.common.shiro.ShiroProperties;
import co.acrch.common.domain.ValidateCodeProperties;
import co.acrch.common.shiro.ShiroProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "acrch")
public class AcrchProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    private String timeFormat = "yyyy-MM-dd HH:mm:ss";

    private boolean openAopLog = true;

    public ShiroProperties getShiro() {
        return shiro;
    }

    public void setShiro(ShiroProperties shiro) {
        this.shiro = shiro;
    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isOpenAopLog() {
        return openAopLog;
    }

    public void setOpenAopLog(boolean openAopLog) {
        this.openAopLog = openAopLog;
    }
}
