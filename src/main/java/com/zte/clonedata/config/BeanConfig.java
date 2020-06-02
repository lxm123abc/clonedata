package com.zte.clonedata.config;

import com.zte.clonedata.util.SpringContextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * ProjectName: clonedata-com.zte.clonedata.config
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 15:27 2020/6/2
 * @Description:
 */
@Configuration
public class BeanConfig {


    @Bean
    public SpringContextUtil springContextHolder(){
        return new SpringContextUtil();
    }

}
