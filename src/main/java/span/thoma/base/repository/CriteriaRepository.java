package span.thoma.base.repository;

import org.springframework.data.repository.CrudRepository;
import span.thoma.base.criteria.Criteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017-10-26.
 */
public interface CriteriaRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    T findOne(Criteria criteria);

    List<T> find(Criteria criteria);

    long count(Criteria criteria);

    boolean exist(Criteria criteria);

}
