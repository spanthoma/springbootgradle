package span.thoma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-10-11.
 */

@Configuration
@EnableOAuth2Client
public class SocialConfig {

    @Autowired
    OAuth2ClientContext oauth2Clientcontext;


    @Autowired
    private ApplicationContext context;


    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }


    @Bean
    @ConfigurationProperties("github.client")
    public AuthorizationCodeResourceDetails github() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("github.resource")
    public ResourceServerProperties githubResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(-100);
        return registrationBean;
    }

    @Bean("sso.filter")
    public Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        OAuth2ClientAuthenticationProcessingFilter facebook = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
        facebook.setRestTemplate(new OAuth2RestTemplate(facebook(), oauth2Clientcontext));
        facebook.setTokenServices(new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
        filters.add(facebook);
        filter.setFilters(filters);
        return filter;
    }


    private OAuth2ClientAuthenticationProcessingFilter getFacebookFilter() {
        return getOAuthFIlter(facebook(), facebookResource(), "/login/facebook");
    }

    private OAuth2ClientAuthenticationProcessingFilter getGithubFilter() {
        return getOAuthFIlter(github(), githubResource(), "/login/github");
    }


    private OAuth2ClientAuthenticationProcessingFilter getOAuthFIlter(AuthorizationCodeResourceDetails details, ResourceServerProperties resource, String url) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(url);
        OAuth2RestTemplate template = new OAuth2RestTemplate(details, oauth2Clientcontext);
        filter.setRestTemplate(template);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), details.getClientId());
        tokenServices.setRestTemplate(template);
        filter.setTokenServices(tokenServices);
        return filter;
    }

}
