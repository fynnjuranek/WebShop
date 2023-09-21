package de.leuphana.webshop.connector.dbconnector;

import de.leuphana.shop.structure.Article;
import de.leuphana.shop.structure.Book;
import de.leuphana.shop.structure.BookCategory;
import de.leuphana.shop.structure.CD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ArticleDatabaseLoader {
    private static final Logger log = LoggerFactory.getLogger(ArticleDatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(ArticleRepository repository) {;
        // TODO add author to books
        Book book1 = new Book();
		book1.setName("Entwickeln von Web-Anwendungen");
        book1.setManufactor("Springer");
		book1.setPrice(new BigDecimal("13.0"));
		book1.setBookCategory(BookCategory.POPULAR_SCIENCE);

        Book book2 = new Book();
		book2.setName("Java in a nutshell");
        book2.setManufactor("O'Reilly Media, Inc.");
		book2.setPrice(new BigDecimal("10.5"));
		book2.setBookCategory(BookCategory.POPULAR_SCIENCE);

        Book book3 = new Book();
		book3.setName("Servlets");
        book3.setManufactor("Springer");
		book3.setPrice(new BigDecimal("16.5"));
		book3.setBookCategory(BookCategory.POPULAR_SCIENCE);

        CD cd1 = new CD();
        cd1.setName("Nancy & Lee");
        cd1.setArtist("Nancy Sinatra");
        cd1.setManufactor("Boots Enterprises Inc.");
        cd1.setPrice(new BigDecimal("11.3"));

        CD cd2 = new CD();
        cd2.setName("Rap über Hass");
        cd2.setArtist("KIZ");
        cd2.setManufactor("Universal Music");
        cd2.setPrice(new BigDecimal("13.9"));

        log.info("Preloading " + repository.save(book1));
        log.info("Preloading " + repository.save(book2));
        log.info("Preloading " + repository.save(book3));
        log.info("Preloading " + repository.save(cd1));
        log.info("Preloading " + repository.save(cd2));
        repository.findAll().forEach(article -> log.info("Preloaded " + article));
        return args -> {
            repository.findAll().forEach(article -> log.info("Preloaded " + article));

        };
    }
}
