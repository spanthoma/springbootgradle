package span.thoma.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by spanthoma on 16. 12. 23..
 */

@Controller
public class SpringBootController {

    @ResponseBody
    @RequestMapping(value = "/test",  method = RequestMethod.GET)
    @Cacheable(value = "BLOG:controller.test")
    public String test(Model model) {
        return "hello springboot gradle222";
    }

    @Cacheable(value = "BLOG:controller.home")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
