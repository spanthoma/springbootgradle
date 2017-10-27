package span.thoma.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import span.thoma.security.dto.User;

import java.io.Serializable;

/**
 * Created by admin on 2017-09-28.
 */
@Getter
@Setter
@TypeAlias("Author")
public class Author extends User implements Serializable{

    private static final long serialVersionUID = 3070954150384723499L;
}
