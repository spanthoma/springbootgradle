package span.thoma.service;

import org.springframework.stereotype.Service;
import span.thoma.model.Blog;

import java.util.List;

/**
 * Created by admin on 2017-09-29.
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Override
    public List<Blog> list() {
        return null;
    }

    @Override
    public void write(Blog blog) {

    }
}
