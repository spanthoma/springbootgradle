package spanthoma.demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by spanthoma on 16. 12. 23..
 */

@Controller
@EnableAutoConfiguration
public class SpringBootController {

    @ResponseBody
    @RequestMapping(value = "/test",  method = RequestMethod.GET)
    public String test(Model model) {
        return "hello springboot gradle222";
    }
}
