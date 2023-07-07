package com.example.bronze.Article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    @GetMapping("/article/list")
    public String list(Model model){
        List<Article> article = this.articleService.getlist();
        model.addAttribute(article);
        return "article_list";
    }

    @GetMapping(value = "/article/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/article/create")
    public String create(){
        return "create_Form";
    }


    @PostMapping("/article/create")
    public String create(@RequestParam String subject, @RequestParam String content){
        this.articleService.create(subject,content);
        return String.format("redirect:/article/list");
    }
}
