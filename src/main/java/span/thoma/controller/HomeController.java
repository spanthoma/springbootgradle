package span.thoma.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import span.thoma.demo.HomeService;

import static span.thoma.common.Constants.CONTENT_KEY;
import static span.thoma.common.Constants.COMMON_INDEX;

/**
 * Created by spanthoma on 16. 12. 23..
 */

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @ResponseBody
    @RequestMapping(value = "/test",  method = RequestMethod.GET)
    public String test(Model model) {
        homeService.test();
        return "hello springboot test";
    }

    @ResponseBody
    @RequestMapping(value = "/test2",  method = RequestMethod.GET)
    public String test2(Model model) {
        return test(model);
    }


    @ResponseBody
    @RequestMapping(value = "/test3",  method = RequestMethod.GET)
    public String test3(Model model) {
        homeService.test3();
        return "hello springboot test3";

    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap modelMap) {
        modelMap.put(CONTENT_KEY, "index");
        homeService.getHome();
        return COMMON_INDEX;
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        return home(modelMap);
    }
}
