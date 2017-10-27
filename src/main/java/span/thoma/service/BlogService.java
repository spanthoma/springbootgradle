package span.thoma.service;

import span.thoma.model.Blog;

import java.util.List;

/**
 * Created by admin on 2017-09-29.
 */
public interface BlogService {

    List<Blog> list();

    int write(Blog blog);
}
