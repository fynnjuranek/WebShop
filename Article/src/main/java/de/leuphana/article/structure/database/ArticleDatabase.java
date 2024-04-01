package de.leuphana.article.structure.database;

import de.leuphana.article.structure.database.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDatabase extends JpaRepository<ArticleEntity, Integer> {

    ArticleEntity findArticleEntityByName(String name);
    ArticleEntity findArticleEntityByArticleId(int articleId);
}
