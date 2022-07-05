package com.wgx.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidateParamConfiguration {

    // 快速失败，一个参数校验失败就不继续校验了，优化
    @Bean
    public LocalValidatorFactoryBean getValidatorFactory() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        return localValidatorFactoryBean;
    }
}
