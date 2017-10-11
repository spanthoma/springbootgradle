package span.thoma.model;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * Created by admin on 2017-09-28.
 */

@Data
public class Blog {

    private long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private boolean lock;
    private boolean lockPassword;

    private Author author;
    private Category category;

}
