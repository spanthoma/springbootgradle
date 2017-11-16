package span.thoma.controller.admin;

import static span.thoma.common.Constants.ADMIN_COMMON_INDEX;
import static span.thoma.common.Constants.TEMPLATE_KEY;
import static span.thoma.common.Constants.CONTENT_KEY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import span.thoma.base.criteria.SimpleCriteria;
import span.thoma.service.BlogService;

/**
 * Created by admin on 2017-11-16.
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogManageController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/list")
    public String list(ModelMap modelMap) {
        modelMap.put(TEMPLATE_KEY, "/admin/blog/list");
        modelMap.put(CONTENT_KEY, blogService.list(SimpleCriteria.newInstance()));
        return ADMIN_COMMON_INDEX;
    }
}
