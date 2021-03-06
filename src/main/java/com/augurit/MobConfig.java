package com.augurit;

import com.augurit.agcloud.framework.config.FrameworkSsoProperties;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableOAuth2Sso
@Configuration
@ComponentScan(basePackages = {"com.augurit.agcloud.framework.config"})
public class MobConfig {//extends WebSecurityConfigurerAdapter{


    @Autowired
    private FrameworkSsoProperties frameworkSsoProperties;

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable();
        if (frameworkSsoProperties.getEnable()) {
            http
                    .logout()
                    .logoutSuccessUrl(frameworkSsoProperties.getSsoLogoutUrl())
                    .and()
                    .authorizeRequests()
                    .antMatchers("/ui-static/**","/rest/**")
                    .permitAll()
                    .anyRequest().authenticated();
        } else {
            //全部资源不需要认证
            http
                    .authorizeRequests()
                    .antMatchers("/**").permitAll();
        }
    }*/

}
