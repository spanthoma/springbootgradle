package span.thoma.repository;

import org.springframework.stereotype.Repository;
import span.thoma.base.repository.CriteriaRepository;
import span.thoma.model.Blog;

import java.util.List;

/**
 * Created by admin on 2017-09-29.
 */
@Repository
public interface BlogRepository extends CriteriaRepository<Blog, Long> {

}
