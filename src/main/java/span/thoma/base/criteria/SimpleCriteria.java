package span.thoma.base.criteria;

import span.thoma.base.model.OrderBy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017-10-26.
 */
public class SimpleCriteria implements Criteria {

    private Map<String, Object> param = new HashMap<>();

    private Map<String, OrderBy> sort = new LinkedHashMap<>();


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

    public Map<String, Object> getParam() {
        return param;
    }

    public Map<String, OrderBy> getSort() {
        return sort;
    }
}
