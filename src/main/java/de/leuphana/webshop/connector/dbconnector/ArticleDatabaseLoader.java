package de.leuphana.webshop.connector.dbconnector;

import de.leuphana.shop.structure.Article;
import de.leuphana.shop.structure.Book;
import de.leuphana.shop.structure.BookCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ArticleDatabaseLoader {
    private static final Logger log = LoggerFactory.getLogger(ArticleDatabaseLoader.class);
    //private static ArticleRepository repository;

    @Bean
    CommandLineRunner initDatabase(ArticleRepository repository) {
        //this.repository = repository;
        Set<Article> articles = new HashSet<>();
        // TODO add author to books
        Book book1 = new Book();
		book1.setName("Entwickeln von Web-Anwendungen");
		book1.setPrice(23.0f);
		book1.setBookCategory(BookCategory.POPULAR_SCIENCE);

		//articles.add(book);

        Book book2 = new Book();
		book2.setName("Java in a nutshell");
		book2.setPrice(10.5f);
		book2.setBookCategory(BookCategory.POPULAR_SCIENCE);

		//articles.add(book);

        Book book3 = new Book();
		book3.setName("Servlets");
		book3.setPrice(16.5f);
		book3.setBookCategory(BookCategory.POPULAR_SCIENCE);

		//articles.add(book);
        log.info("Preloading " + repository.save(book1));
        log.info("Preloading " + repository.save(book2));
        log.info("Preloading " + repository.save(book3));
        repository.findAll().forEach(article -> log.info("Preloaded " + article));
        return args -> {
            repository.findAll().forEach(article -> log.info("Preloaded " + article));

        };
    }

//    public static Set<Article> fetchArticles() {
//        Set<Article> articles = new HashSet<>();
//        articles.addAll(repository.findAll());
//        return articles;
//    }

}
