package de.leuphana.connector;

import de.leuphana.article.behaviour.ArticleService;
import de.leuphana.article.structure.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ArticleRestConnectorProvider {

    @Autowired
    ArticleService articleService;

    static final Logger LOGGER = LoggerFactory.getLogger(ArticleRestConnectorProvider.class);

    @RequestMapping("/getArticle/{articleId}")
    public Article findArticleByArticleId(@PathVariable("articleId") Integer articleId) {
        LOGGER.info("RestCall (url) '/getArticle/{articleId}' got called");
        return articleService.findArticleById(articleId);
    }

    @RequestMapping("/getArticles")
    public List<Article> findAllArticles() {
        LOGGER.info("RestCall (url) '/getArticles' got called");
        return articleService.findAllArticles();
    }

    @PostMapping("/addArticle")
    public Article addArticleToDatabase(@RequestBody Article article) {
        LOGGER.info("RestCall (url) '/addArticle' got called");
        return articleService.addArticleToDatabase(article);
    }

    @RequestMapping("/deleteArticle/{articleId}")
    public boolean deleteArticleById(@PathVariable("articleId") Integer id) {
        LOGGER.info("RestCall (url) '/deleteArticle/{articleId}' got called");
        return articleService.deleteArticleByArticleId(id);
    }



}
