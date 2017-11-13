package span.thoma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import span.thoma.base.criteria.Criteria;
import span.thoma.base.criteria.SimpleCriteria;
import span.thoma.base.model.PageResult;
import span.thoma.dto.BlogContent;
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
    public BlogContent list(Criteria criteria) {
        BlogContent blogContent = new BlogContent();
        List<Blog> list = blogRepository.find(criteria);
        blogContent.setBlogList(new PageResult<Blog>(list, criteria));
        return blogContent;
    }

    @Override
    public int write(Blog blog) {
        blogRepository.save(blog);
        return 0;
    }

    @Override
    public Blog findOne(long blogId) {
        return blogRepository.findOne(blogId);
    }


}
