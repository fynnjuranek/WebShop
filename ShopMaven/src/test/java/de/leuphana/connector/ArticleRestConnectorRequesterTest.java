package de.leuphana.connector;


import de.leuphana.shop.structure.article.Article;

import de.leuphana.shop.structure.article.Book;
import de.leuphana.shop.structure.article.BookCategory;
import de.leuphana.shop.structure.article.CD;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleRestConnectorRequesterTest {

    @Autowired
    ArticleRestConnectorRequester articleRestConnectorRequester;

    static Book book;
    static CD cd;
    static Article addedBook;
    static Article addedCD;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setName("Sprechen Sie Java?");
        book.setManufacturer("d.punkt.verlag");
        book.setAuthor("Hanspeter Mössenböck");
        book.setPrice(29.90f);
        book.setBookCategory(BookCategory.POPULAR_SCIENCE);

        cd = new CD();
        cd.setName("Vampire Weekend");
        cd.setManufacturer("XL Recordings Ltd");
        cd.setArtist("Vampire Weekend");
        cd.setPrice(8.49f);
    }

    // Test works if Gateway and Article are started already
    // maybe it needs 1-2 seconds so article is 100% registered on the discovery-service (eureka-server)
    @Test
    @Order(1)
    void canBookBeAdded() {
        addedBook = articleRestConnectorRequester.addArticle(book);
        System.out.println("Added article to database: " + book.getName());
        Assertions.assertNotNull(addedBook);
    }

    @Test
    @Order(2)
    void canCDBeAdded() {
        addedCD = articleRestConnectorRequester.addArticle(cd);
        System.out.println("Added article to database: " + cd.getName());
        Assertions.assertNotNull(addedCD);
    }

    @Test
    @Order(3)
    void canBookBeFoundById() {
        Article foundArticle = articleRestConnectorRequester.getArticleByArticleId(addedBook.getArticleId());
        Book foundBook = null;
        if (foundArticle instanceof Book) {
            foundBook = (Book) foundArticle;
            // Use book specific information to show that there is no information loss
            System.out.println("Found book with Author: " + foundBook.getAuthor());
        }
        Assertions.assertNotNull(foundBook);
    }

    @Test
    @Order(4)
    void canCDBeFoundByName() {
        Article foundArticle = articleRestConnectorRequester.getArticleByArticleId(addedCD.getArticleId());
        CD foundCD = null;
        if (foundArticle instanceof CD) {
            foundCD = (CD) foundArticle;
            // Use CD specific information to show that there is no information loss
            System.out.println("Found CD with artist: " + foundCD.getArtist());
        }
        Assertions.assertNotNull(foundCD);
    }

    @Test
    @Order(5)
    void canArticlesBeFound() {
        List<Article> articles = articleRestConnectorRequester.getArticles();
        System.out.println("Found articles with names: ");
        for (Article article : articles) {
            System.out.println(article.getName());
        }
        // assert articles == not empty
        Assertions.assertFalse(articles.isEmpty());
    }

    @Test
    @Order(6)
    void canArticleBeDeleted() {
        boolean isDeleted = articleRestConnectorRequester.deleteArticleByArticleId(addedCD.getArticleId());
        System.out.println("Successfully deleted article with id: " + addedCD.getArticleId() + " ? " + isDeleted);
        Assertions.assertTrue(isDeleted);
    }

}
