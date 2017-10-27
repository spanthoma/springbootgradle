package span.thoma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import span.thoma.base.criteria.Criteria;
import span.thoma.base.criteria.SimpleCriteria;
import span.thoma.model.Blog;
import span.thoma.repository.BlogRepository;

import java.util.List;

/**
 * Created by admin on 2017-09-29.
 */
@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> list() {
        Criteria criteria = SimpleCriteria.newInstance();
        return blogRepository.find(criteria);
    }

    @Override
    public int write(Blog blog) {
        blogRepository.save(blog);
        return 0;
    }
}
