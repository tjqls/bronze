package com.example.bronze.Article;

import com.example.bronze.User.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    public Page<Article> getlist(int page,String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Article> spec = search(kw);
        return this.articleRepository.findAll(spec, pageable);
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

    public void delete(Article article){
        this.articleRepository.delete(article);
    }

    private Specification<Article> search(String kw){
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);  // 중복을 제거
                Join<Article, SiteUser> u1 = root.join("author", JoinType.LEFT);
                return criteriaBuilder.or(criteriaBuilder.like(root.get("subject"), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("content"), "%" + kw + "%"),
                        criteriaBuilder.like(u1.get("username"), "%" + kw + "%"));
            }
        };
    }
}
