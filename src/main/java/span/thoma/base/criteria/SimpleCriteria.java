package span.thoma.base.criteria;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import span.thoma.base.model.OrderBy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by admin on 2017-10-26.
 */

@Getter
@Alias("criteria")
public class SimpleCriteria implements Criteria {

    private static final long serialVersionUID = 3636310937187715236L;

    private Map<String, Object> param = new HashMap<>();
    private Map<String, OrderBy> sort = new LinkedHashMap<>();

    private int pageNumber = UNDEFINED_PAGE_VALUE;
    private int pageItemSize = UNDEFINED_PAGE_VALUE;

    private SimpleCriteria(){}

    public static Criteria newInstance() {
        return new SimpleCriteria();
    }

    public Criteria add(String key, Object value) {
        this.param.put(key, value);
        return this;
    }

    public Criteria sort(String field, OrderBy orderBy) {
        this.sort.put(field, orderBy);
        return this;
    }

    public void setPage(int pageNumber) {
        setPage(pageNumber, DEFAULT_PAGEITEM_SIZE);
    }
    public void setPage(int pageNumber, int pageItemSize) {
        this.pageNumber = pageNumber;
        this.pageItemSize = pageItemSize;
    }



}
