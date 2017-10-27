package span.thoma.service;

import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
@SpringBootTest(classes = {BlogService.class})
@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Log4j
public class BlogServiceTest {

    @MockBean
    private BlogService blogService;

    private List<Blog> list = new ArrayList<>();
    @Before
    public void init() {
        list.add(new Blog("title", "content1"));
        list.add(new Blog("title2", "content2"));
    }

    @Test
    public void testList() throws Exception {
        given(blogService.list()).willReturn(list);

        List<Blog> testList = blogService.list();
        assertThat(testList.get(0).getTitle(), is("title"));
    }

    @Test
    public void testWrite() throws Exception {

        Blog blog = new Blog("title1", "this is test for write");
        blog.setCategory(new Category(1));
        blog.setAuthor(new Author());
        blog.getAuthor().setUsername("test@test.com");

        int blogId = blogService.write(blog);

    }
}