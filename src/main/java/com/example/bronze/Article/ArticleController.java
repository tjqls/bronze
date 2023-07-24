package com.example.bronze.Article;

import com.example.bronze.User.SiteUser;
import com.example.bronze.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    @GetMapping("/article/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue="0") int page,
                       @RequestParam(value="kw",defaultValue = "0") String kw){
        Page<Article> paging = this.articleService.getlist(page,kw);
        model.addAttribute("paging",paging);
        model.addAttribute("kw",kw);
        return "article_list";
    }

    @GetMapping(value = "/article/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "article_detail";
    }

    @GetMapping("/article/create")
    @PreAuthorize("isAuthenticated()")
    public String articleCreate(){
        return "article_create";
    }


    @PostMapping("/article/create")
    @PreAuthorize("isAuthenticated()")
    public String articleCreate(@RequestParam String subject, @RequestParam String content, Principal principal){
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.articleService.create(subject,content, siteUser);
        return String.format("redirect:/article/list");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/modify/{id}")
    public String questionModify(ArticleForm articleForm, @PathVariable("id") Integer id, Principal principal) {
        Article article = this.articleService.getArticle(id);
        if(!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        articleForm.setSubject(article.getSubject());
        articleForm.setContent(article.getContent());

        return "article_create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/article/modify/{id}")
    public String articleModify(@Valid ArticleForm articleForm, BindingResult bindingResult,
                                Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Article article = this.articleService.getArticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.articleService.modify(article, articleForm.getSubject(), articleForm.getContent());
        return String.format("redirect:/article/detail/%s", id);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/article/delete/{id}")
    public String articleDelete(Principal principal, @PathVariable("id") Integer id) {
        Article article = this.articleService.getArticle(id);
        if (!article.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.articleService.delete(article);
        return "redirect:/";
    }
}
