package de.leuphana.connector;


import de.leuphana.shop.structure.article.Article;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("Article")
public interface ArticleRestConnectorRequester {

    @RequestMapping("/getArticle/{articleId}")
    Article getArticleByArticleId(@PathVariable("articleId") Integer articleId);

    @RequestMapping("/getArticles")
    List<Article> getArticles();

    @PostMapping("/addArticle")
    Article addArticle(@RequestBody Article article);


    @RequestMapping("/deleteArticle/{articleId}")
    boolean deleteArticleByArticleId(@PathVariable("articleId") Integer articleId);



}
