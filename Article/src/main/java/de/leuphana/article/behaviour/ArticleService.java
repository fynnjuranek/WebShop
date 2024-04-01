package de.leuphana.article.behaviour;

import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.ArticleEntity;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.entity.CdEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.article.structure.Article;
import de.leuphana.article.structure.Book;
import de.leuphana.article.structure.CD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Service is used for components that hold business logic -> data manipulation...
// https://stackoverflow.com/questions/58234187/what-is-the-use-of-service-layer-in-spring-boot-applications

@Service
public class ArticleService {

    @Autowired
    ArticleDatabase articleDatabase;

    @Autowired
    ArticleMapper articleMapper;

    static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    public Article addArticleToDatabase(Article article) {
        ArticleEntity foundArticleEntity = articleDatabase.findArticleEntityByName(article.getName());
        if (foundArticleEntity != null) {
            articleDatabase.deleteById(foundArticleEntity.getArticleId());
            LOGGER.info("Article got deleted out of the database because of duplication. ArticleID: {}", foundArticleEntity.getArticleId());
        }

        ArticleEntity articleEntity = null;
        if (article instanceof Book) {           // there would be information loss if no type-cast
            articleEntity = articleMapper.mapToBookEntity((Book) article);
        } else if (article instanceof CD) {
            articleEntity = articleMapper.mapToCdEntity((CD) article);
        }

        // To be 100% sure that the entity got properly saved!
        ArticleEntity savedArticleEntity = null;
        if (articleEntity != null) {
            savedArticleEntity = articleDatabase.save(articleEntity);
            LOGGER.info("Article with id: {} was added to the database", savedArticleEntity.getArticleId());
        } else {
            LOGGER.error("ArticleEntity with name: {} was not added to the database, probably couldn't get properly mapped", article.getName());
        }

        return articleMapper.mapToArticle(savedArticleEntity);
    }

    public Article findArticleById(int articleId) {
        ArticleEntity articleEntity = articleDatabase.findArticleEntityByArticleId(articleId);
        Article article = null;
        if (articleEntity instanceof BookEntity) {
            article = articleMapper.mapToBook((BookEntity) articleEntity);
        } else if (articleEntity instanceof CdEntity) {
            article = articleMapper.mapToCd((CdEntity) articleEntity);
        } else {
            LOGGER.error("ArticleEntity with id {} was not able to get mapped!", articleEntity.getArticleId());
        }
        return article;
    }

    public List<Article> findAllArticles() {
        List<ArticleEntity> articleEntities = articleDatabase.findAll();
        List<Article> articles = new ArrayList<>();
        for (ArticleEntity articleEntity : articleEntities) {
            if (articleEntity instanceof BookEntity) {
                articles.add(articleMapper.mapToBook((BookEntity) articleEntity));
            } else if (articleEntity instanceof CdEntity) {
                articles.add(articleMapper.mapToCd((CdEntity) articleEntity));
            } else {
                LOGGER.error("ArticleEntity with id {} couldn't get mapped!", articleEntity.getArticleId());
            }
        }
        return articles;
    }

    public boolean deleteArticleByArticleId(Integer articleId) {
        boolean isDeleted = false;
        articleDatabase.deleteById(articleId);
        if (articleDatabase.findArticleEntityByArticleId(articleId) == null) {
            isDeleted = true;
            LOGGER.info("Article with id: {} was deleted", articleId);
        }
        return isDeleted;
    }

}
