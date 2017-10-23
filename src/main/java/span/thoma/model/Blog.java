package span.thoma.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by admin on 2017-09-28.
 */

@Data
public class Blog {

    private long id;

    @NonNull
    @Size(max = 100, message = "error.blog.title.size")
    @NotEmpty(message = "error.blog.title.empty")
    private String title;

    @NonNull
    @NotEmpty(message = "error.blog.content.empty")
    private String content;

    private boolean lock;
    private boolean lockPassword;

    private Author author;
    private Category category;

}
