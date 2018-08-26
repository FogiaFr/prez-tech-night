package com.mkl.prez.technight;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.mkl.prez.technight.EnumsAntiSeche.*;
import static org.mockito.Mockito.when;

/**
 * Description of the class.
 *
 * @author MKL.
 */
@RunWith(MockitoJUnitRunner.class)
public class EnumsAntiSecheTest {
    private static final double EPSILON = 0.0001d;
    @InjectMocks
    private BookService bookService;

    @Mock
    private ConfigurationDao configurationDao;

    @Test
    public void testBookPrice() {
        BookPriceBuilder.create(configurationDao)
                .book(BookBuilder.create().genre(BookGenreEnum.MYSTERY).basePrice(12d).book())
                .whenGetBookPrice(bookService)
                .thenResultShouldBe(12d);

        BookPriceBuilder.create(configurationDao)
                .book(BookBuilder.create().genre(BookGenreEnum.FANTASY).basePrice(10d).book())
                .whenGetBookPrice(bookService)
                .thenResultShouldBe(10d);

        BookPriceBuilder.create(configurationDao)
                .book(BookBuilder.create().genre(BookGenreEnum.ROMANCE).basePrice(11d).book())
                .romanceFactor(2d)
                .whenGetBookPrice(bookService)
                .thenResultShouldBe(22d);
    }

    private static class BookPriceBuilder {
        ConfigurationDao configurationDao;
        Double romanceFactor;
        Book book;
        Double result;

        private BookPriceBuilder(ConfigurationDao configurationDao) {
            this.configurationDao = configurationDao;
        }

        static BookPriceBuilder create(ConfigurationDao configurationDao) {
            return new BookPriceBuilder(configurationDao);
        }

        BookPriceBuilder book(Book book) {
            this.book = book;
            return this;
        }

        BookPriceBuilder romanceFactor(Double romanceFactor) {
            this.romanceFactor = romanceFactor;
            return this;
        }

        BookPriceBuilder whenGetBookPrice(BookService bookService) {
            if (romanceFactor != null) {
                when(configurationDao.getRomanceFactor()).thenReturn(romanceFactor);
            }

            result = bookService.getBookPrice(book);

            return this;
        }

        void thenResultShouldBe(Double result) {
            Assert.assertEquals(result, this.result, EPSILON);
        }
    }

    private static class BookBuilder {
        static long cpt = 1;
        BookGenreEnum genre;
        double basePrice;

        static BookBuilder create() {
            return new BookBuilder();
        }

        BookBuilder genre(BookGenreEnum genre) {
            this.genre = genre;
            return this;
        }

        BookBuilder basePrice(double basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        Book book() {
            return new Book(cpt++, "usless name", genre, basePrice);
        }
    }
}
