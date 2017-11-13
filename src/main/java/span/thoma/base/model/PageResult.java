package span.thoma.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import span.thoma.base.criteria.Criteria;
import span.thoma.model.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017-11-08.
 */

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PageResult<T> implements Page<T> {

    private static final long serialVersionUID = 658957162900505012L;

    private int pageNumber;
    private int pageItemSize;
    private int totalItemSize;
    private List<T> content = new ArrayList<>();

    public PageResult(List<T> list, Criteria criteria) {
        this.content = list;
        setPageNumber(criteria.getPageNumber());
        setPageItemSize(criteria.getPageItemSize());
    }
}
