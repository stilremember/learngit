package com.augurit;

import com.augurit.agcloud.framework.config.FrameworkSsoProperties;
import com.augurit.myproject.utils.plugin.schedule.core.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;


@SpringBootApplication
@ComponentScan(basePackages = {"com.augurit","org.springside.modules"})
@EntityScan(basePackages = "com.augurit.**.entity")
public class MobApp extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(MobApp.class);


    public static void main(String[] args) {
        SpringApplication.run(MobApp.class, args);
    }

    //初始化spring定时工厂
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        return new SchedulerFactoryBean();
    }
    @Bean
    public SpringUtils springUtils(){
        return new SpringUtils();
    }
    @Bean
    public FrameworkSsoProperties frameworkSsoProperties(){
       return new FrameworkSsoProperties();
    }


    /**
     * hibernate所使用的sessionFactory
     * @return
     */
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    /**
     * 配置监听器
     * @return
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

}
