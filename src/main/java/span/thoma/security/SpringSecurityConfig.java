package span.thoma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import span.thoma.security.service.UserService;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by admin on 2017-07-31.
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext context;



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/**/js/**", "/**/css/**", "/**/img/**", "/**/fonts/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/register", "/login/**")
                    .permitAll().and()
                .authorizeRequests()
                    .antMatchers("/template/admin/**").hasRole("ADMIN").and()
                .authorizeRequests()
                    .anyRequest().hasAnyRole("USER", "OAUTH").and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/user/home")
                    .successHandler(authenticationSuccessHandler())
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/user/home")
                        .permitAll()
                .and().addFilterBefore((Filter) context.getBean("sso.filter"), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.passwordEncoder());
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        AuthenticationSuccessHandler successHandler = new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                if(roles.contains("ADMIN")) {
                    response.sendRedirect("/admin/home");
                }
            }
        };
        return successHandler;
    }







}
