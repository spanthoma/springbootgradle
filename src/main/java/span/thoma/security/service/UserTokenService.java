package span.thoma.security.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import span.thoma.security.dto.RoleType;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017-10-19.
 */
public class UserTokenService extends UserInfoTokenServices {

    public UserTokenService(String userInfoEndpointUrl, String clientId) {
        super(userInfoEndpointUrl, clientId);
        setAuthoritiesExtractor(new OAuth2AuthoritiesExtractor());
    }


    public static class OAuth2AuthoritiesExtractor implements AuthoritiesExtractor {
        @Override
        public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
            return AuthorityUtils.createAuthorityList(RoleType.OAHTH.toString());
        }
    }
}
