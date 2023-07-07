package com.example.bronze;

import com.example.bronze.Article.Article;
import com.example.bronze.Article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BronzeApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void contextLoads() {
		Article article = new Article();
		article.setSubject("승급");
		article.setContent("승급시험중");
		article.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(article);
	}

}
