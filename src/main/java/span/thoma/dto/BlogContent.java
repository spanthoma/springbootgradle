package span.thoma.dto;

import lombok.Getter;
import lombok.Setter;
import span.thoma.base.model.Page;
import span.thoma.model.Blog;

import java.io.Serializable;

/**
 * Created by admin on 2017-11-09.
 */
@Getter
@Setter
public class BlogContent implements Serializable {

    private Page<Blog> blogList;

}
