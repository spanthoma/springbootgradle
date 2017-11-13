package span.thoma.base.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017-11-08.
 */
public interface Page<T> extends Pagination, Serializable {

    List<T> getContent();

}
