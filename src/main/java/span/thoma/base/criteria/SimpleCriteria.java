package span.thoma.base.criteria;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2017-10-26.
 */
public class SimpleCriteria implements Criteria {

    private Map<String, Object> param = new HashMap<>();
    private Map<String, Object> order = new HashMap<>();
    private Map<String, Object> sort = new HashMap<>();

    private SimpleCriteria(){}

    public static Criteria newInstance() {
        return new SimpleCriteria();
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public Map<String, Object> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }

    public Map<String, Object> getSort() {
        return sort;
    }

    public void setSort(Map<String, Object> sort) {
        this.sort = sort;
    }
}
