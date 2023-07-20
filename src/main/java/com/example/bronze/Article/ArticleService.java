package com.example.bronze.Article;

import com.example.bronze.User.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    public List<Article> getlist(){
        return articleRepository.findAll();
    }

    public Article getArticle(Integer id){
        Optional<Article> getId = articleRepository.findById(id);
        if(getId.isPresent()){
            return getId.get();
        }else{
            return null;
        }
    }

    public void create(String subject, String content, SiteUser author){
        Article a = new Article();
        a.setSubject(subject);
        a.setContent(content);
        a.setAuthor(author);
        a.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(a);
    }

    public void modify(Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }
}
