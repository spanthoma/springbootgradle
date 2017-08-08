package span.thoma.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import span.thoma.security.dto.User;

import java.util.Collection;

/**
 * Created by admin on 2017-08-07.
 */
public interface UserService extends UserDetailsService{
    Collection<GrantedAuthority> getAuthorities(String username);

    User readUser(String username);
    void createUser(User user);
    void deleteUser(String username);
    PasswordEncoder passwordEncoder();
}
