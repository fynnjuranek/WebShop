package de.leuphana.webshop.connector.dbconnector;

import de.leuphana.shop.structure.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
