package span.thoma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;
import span.thoma.security.service.UserService;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-07-31.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private UserService userService;
/*
    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;*/


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/register")
                    .permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                    .loginPage("/login**")
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/home")
                .permitAll();
                //.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
    }
/*
    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(getOAuthFIlter(facebook(), facebookResource(), "/login/facebook"));
        filter.setFilters(filters);
        return filter;
    }

    @Bean
    @ConfigurationProperties(prefix = "facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties(prefix = "facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    private OAuth2ClientAuthenticationProcessingFilter getOAuthFIlter(AuthorizationCodeResourceDetails details, ResourceServerProperties resource, String url) {

        System.out.println("----------------------------------");
        System.out.println(details.getAccessTokenUri());
        System.out.println("----------------------------------");
        System.out.println(resource.getClientId());
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(url);
        OAuth2RestTemplate template = new OAuth2RestTemplate(details,  oAuth2ClientContext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), details.getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientfilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(-100);
        return registrationBean;
    }*/
}
