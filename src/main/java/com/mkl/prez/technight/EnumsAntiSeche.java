package com.mkl.prez.technight;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class EnumsAntiSeche {

    static class BookService {
        ConfigurationDao configurationDao;

        Double getBookPrice(Book book) {
            return book.getGenre().getPriceFunction().apply(this, book);
        }

        Double getBasePrice(Book book) {
            return book.getBasePrice();
        }

        Double getRomancePrice(Book book) {
            return book.getBasePrice() * configurationDao.getRomanceFactor();
        }
    }

    interface ConfigurationDao {
        double getRomanceFactor();
    }

    @Data
    static class Book {
        private final Long id;
        private final String name;
        private final BookGenreEnum genre;
        private final double basePrice;
        private LocalDateTime creationDate;
    }

    enum BookGenreEnum {
        MYSTERY(BookService::getBasePrice),
        FANTASY(BookService::getBasePrice),
        ROMANCE(BookService::getRomancePrice);

        private BiFunction<BookService, Book, Double> priceFunction;

        BookGenreEnum(BiFunction<BookService, Book, Double> priceFunction) {
            this.priceFunction = priceFunction;
        }

        /** @return the priceFunction. */
        public BiFunction<BookService, Book, Double> getPriceFunction() {
            return priceFunction;
        }
    }
}
