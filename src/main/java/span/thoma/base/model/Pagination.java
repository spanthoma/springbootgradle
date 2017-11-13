package span.thoma.base.model;

import java.io.Serializable;

/**
 * Created by admin on 2017-11-09.
 */
public interface Pagination extends Serializable {

    static final int UNDEFINED_PAGE_VALUE = 0;
    static final int DEFAULT_PAGEITEM_SIZE = 30;

    int getPageNumber();
    int getPageItemSize();
}
