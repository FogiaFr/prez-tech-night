package com.mkl.prez.technight;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description of the class.
 *
 * @author MKL.
 */
public class Enums {

    static class BookService {
        ConfigurationDao configurationDao;

        Double getBookPrice(Book book) {
            Double price = null;
            switch (book.getGenre()) {
                case MYSTERY:
                case FANTASY:
                    price = book.getBasePrice();
                    break;
                case ROMANCE:
                    price = book.getBasePrice() * configurationDao.getRomanceFactor();
                    break;
            }

            return price;
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
        MYSTERY,
        FANTASY,
        ROMANCE
    }
}
