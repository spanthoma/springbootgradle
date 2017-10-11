package span.thoma.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by admin on 2017-09-28.
 */

@Data
public class Category implements Serializable{

    private static final long serialVersionUID = 3769380561756283717L;

    private long id;
    private String name;

}
