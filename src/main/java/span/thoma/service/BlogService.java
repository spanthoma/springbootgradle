package span.thoma.service;

import span.thoma.base.criteria.Criteria;
import span.thoma.dto.BlogContent;
import span.thoma.model.Blog;

import java.util.List;

/**
 * Created by admin on 2017-09-29.
 */
public interface BlogService {

    Blog findOne(long blogId);
    BlogContent list(Criteria criteria);

    int write(Blog blog);
}
