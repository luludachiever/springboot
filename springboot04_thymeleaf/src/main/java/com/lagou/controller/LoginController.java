package com.lagou.controller;

import com.lagou.pojo.Article;
import com.lagou.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/")
    public String toLoginPage(Model model){

        //model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        //return "templates/client/index";
        List<Article> article = articleRepository.findAll();
        model.addAttribute("articles", article);
        return "index";
    }

    @RequestMapping("/pagination")
    public String Pagination(HttpServletRequest request,
                             Model model){

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 10; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        model.addAttribute("pages", articleRepository.findAll(PageRequest.of(page, size)));
        return "pagination";

    }


}
