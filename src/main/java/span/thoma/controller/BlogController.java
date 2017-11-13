package span.thoma.controller;

import static span.thoma.common.Constants.TEMPLATE_KEY;
import static span.thoma.common.Constants.COMMON_INDEX;
import static span.thoma.common.Constants.CONTENT_KEY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import span.thoma.base.criteria.Criteria;
import span.thoma.base.criteria.SimpleCriteria;
import span.thoma.model.Author;
import span.thoma.model.Blog;
import span.thoma.service.BlogService;

import javax.validation.Valid;

/**
 * Created by admin on 2017-09-28.
 */
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping("/list")
    public String list(ModelMap modelMap, SimpleCriteria criteria) {
        modelMap.put(TEMPLATE_KEY,"/blog/list");
        modelMap.put(CONTENT_KEY, blogService.list(criteria));
        return COMMON_INDEX;
    }

    @GetMapping("/write")
    public String write(ModelMap modelMap) {
        modelMap.put(TEMPLATE_KEY, "/blog/writeForm");
        return COMMON_INDEX;
    }

    @PostMapping("/write")
    public String write(ModelMap modelMap, @Valid Blog blog) {
        blog.setAuthor(getAuthor());
        return "redirect:/blog/list";
    }

    private Author getAuthor() {
        return (Author)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
