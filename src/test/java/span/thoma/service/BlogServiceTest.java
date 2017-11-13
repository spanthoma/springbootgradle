package span.thoma.service;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import span.thoma.base.criteria.SimpleCriteria;
import span.thoma.config.DataSourceConfig;
import span.thoma.dto.BlogContent;
import span.thoma.model.Author;
import span.thoma.model.Blog;
import span.thoma.model.Category;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by admin on 2017-10-23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = true)
@Log4j
public class BlogServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BlogService blogService;

    private List<Blog> list = new ArrayList<>();
    @Before
    public void init() {
        list.add(new Blog("title", "content1"));
        list.add(new Blog("title2", "content2"));
    }

//    @Test
    public void testList() throws Exception {
        //given(blogService.list()).willReturn(list);

        BlogContent content = blogService.list(SimpleCriteria.newInstance());
        assertTrue(content.getBlogList().getContent().size() == 0);
        //assertThat(testList.get(0).getTitle(), is("title"));
    }

    @Test
    public void testWrite() throws Exception {

        Blog blog = new Blog("title1", "this is test for write");
        blog.setCategory(new Category(1));
        blog.setAuthor(new Author());
        blog.getAuthor().setUsername("test@test.com");

        int blogId = blogService.write(blog);

        Blog insertedBlog = blogService.findOne(blogId);
        assertThat(insertedBlog.getTitle(), is(blog.getTitle()));
    }
}