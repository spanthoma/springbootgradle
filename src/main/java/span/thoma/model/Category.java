package span.thoma.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.TypeAlias;

import java.io.Serializable;

/**
 * Created by admin on 2017-09-28.
 */

@Data
@TypeAlias("Category")
public class Category implements Serializable{

    private static final long serialVersionUID = 3769380561756283717L;

    @NonNull
    private long id;
    private String name;

}
