package span.thoma.base.model;

/**
 * Created by admin on 2017-11-06.
 */
public enum OrderBy {

    ASC("ASC"), DESC("DESC");

    private String order;

    OrderBy(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

}
