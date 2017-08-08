package span.thoma.security.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.junit4.SpringRunner;
import span.thoma.Application;
import span.thoma.security.dto.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by admin on 2017-08-08.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    private User user1;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("pass1");
        user1.setIsAccountNonExpired(true);
        user1.setIsAccountNonLocked(true);
        user1.setName("USER1");
        user1.setIsCredentialsNonExpired(true);
        user1.setIsEnabled(true);
        user1.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
    }


    @Test
    public void createUserTest() {
        userService.deleteUser(user1.getUsername());
        userService.createUser(user1);

        User user = userService.readUser(user1.getUsername());
        assertThat(user.getUsername(), is(user1.getUsername()));
    }
}