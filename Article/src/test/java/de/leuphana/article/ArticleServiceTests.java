package de.leuphana.article;

import de.leuphana.article.behaviour.ArticleService;
import de.leuphana.article.structure.database.ArticleDatabase;
import de.leuphana.article.structure.database.entity.BookEntity;
import de.leuphana.article.structure.database.entity.CdEntity;
import de.leuphana.article.structure.database.mapper.ArticleMapper;
import de.leuphana.article.structure.Article;
import de.leuphana.article.structure.Book;
import de.leuphana.article.structure.BookCategory;
import de.leuphana.article.structure.CD;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleServiceTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleDatabase articleDatabase;

    @Autowired
    ArticleService articleService;


    static CD cd;
    static Book book;
    static Article addedBook;
    static Article addedCD;

    // TODO: Put mapping tests in separate test-class
    // TODO: Change name of test
    @Test
    @Order(1)
    void canBookEntityBeMapped() {
        BookEntity bookEntity = new BookEntity(
                "Addison-Wesley", "The C++ Programming Language", 64.99f, "Bjarne Stroustrup", BookCategory.POPULAR_SCIENCE.toString()
        );
        book = articleMapper.mapToBook(bookEntity);
        System.out.println("Mapped bookEntity to book: " + book.getAuthor());
        Assertions.assertEquals("Bjarne Stroustrup", book.getAuthor());
    }

    @Test
    @Order(2)
    void canCdEntityBeMapped() {
        CdEntity cdEntity = new CdEntity(
                "XL Recordings Ltd", "Vampire Weekend", 8.49f, "Vampire Weekend"
        );
        cd = articleMapper.mapToCd(cdEntity);
        System.out.println("Mapped cdEntity to cd with: " + cd.getArtist());
        Assertions.assertEquals("Vampire Weekend", cd.getArtist());
    }

    @Test
    @Order(3)
    void canBookEntityBeAdded() {
        addedBook = articleService.addArticleToDatabase(book);
        System.out.println("Successfully added: " + addedBook.getName());
        Assertions.assertNotNull(addedBook);
    }

    @Test
    @Order(4)
    void canCdEntityBeAdded() {
        addedCD = articleService.addArticleToDatabase(cd);
        System.out.println("Successfully added: " + addedCD.getName());
        Assertions.assertNotNull(addedCD);
    }

    @Test
    @Order(5)
    void canBookBeFound() {
        Article article = articleService.findArticleById(addedBook.getArticleId());
        Book foundBook = null;
        if (article instanceof Book) {
            foundBook = (Book) article;
            System.out.println("Successfully found book: " + foundBook.getAuthor());
        }
        Assertions.assertNotNull(foundBook);
    }

    @Test
    @Order(6)
    void canCdBeFound() {
        Article article = articleService.findArticleById(addedCD.getArticleId());
        CD foundCD = null;
        if (article instanceof CD) {
            foundCD = (CD) article;
            System.out.println("Successfully found cd: " + foundCD.getArtist());
        }
        Assertions.assertNotNull(foundCD);
    }

    @Test
    @Order(7)
    void canArticleBeDeleted() {
        boolean isDeleted = articleService.deleteArticleByArticleId(addedCD.getArticleId());
        System.out.println("Successfully deleted article? " + isDeleted);
        Assertions.assertTrue(isDeleted);
    }

}
