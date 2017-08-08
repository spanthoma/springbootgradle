package span.thoma.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringBootController.class)
public class SpringbootApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() {

		try {
			mvc.perform(
                    get("/test").contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isUnauthorized());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
