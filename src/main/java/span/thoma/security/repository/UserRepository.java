package span.thoma.security.repository;

import org.springframework.stereotype.Repository;
import span.thoma.security.dto.User;

import java.util.List;

/**
 * Created by admin on 2017-08-07.
 */
@Repository
public interface UserRepository {
    User readUser(String username);
    List<String> readAuthority(String username);

    void createUser(User user);
    void createAuthority(User user);

    void deleteUser(String username);
    void deleteAuthority(String username);

}
