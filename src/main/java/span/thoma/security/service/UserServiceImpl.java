package span.thoma.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import span.thoma.security.repository.UserRepository;
import span.thoma.security.dto.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2017-08-07.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public Collection<GrantedAuthority> getAuthorities(String username) {
        List<String> autoritieStrings = userRepository.readAuthority(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(String authority : autoritieStrings) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.readUser(username);
        user.setAuthorities(getAuthorities(username));
        return user;
    }


    @Override
    public User readUser(String username) {
        User user = userRepository.readUser(username);
        user.setAuthorities(getAuthorities(username));
        return user;
    }

    @Override
    public void createUser(User user) {
        String password = user.getPassword();
        String encodedPassword  = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encodedPassword);
        userRepository.createUser(user);
        userRepository.createAuthority(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUser(username);
        userRepository.deleteAuthority(username);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }
}
