package by.tms.bookshop.service;

import by.tms.bookshop.entity.Book;
import by.tms.bookshop.entity.User;
import by.tms.bookshop.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final Logger logger = LogManager.getLogger(this.getClass());


    @Autowired
    BookRepository bookRepository;

    public boolean addBook(Book book) {
        if (book != null) {
            logger.debug("The book has been successfully added to the database");
            bookRepository.save(book);
            return true;
        }
        logger.debug("The book has not been saved (book = null");
        return false;
    }

    public Iterable<Book> findAllBooks() {
        logger.debug("Display information about all known book");
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(long id) {
        if (bookRepository.findById(id) == null) {
            logger.debug("The book was not found (bookRepository.findById(id)==null)");
        }
        logger.debug("The book was found");
        return bookRepository.findById(id);
    }


    public boolean existBookById(long id) {
        return bookRepository.existsById(id);
    }


    public boolean deleteBook(Book book) {
        if (book != null) {
            logger.debug("The book was successfully deleted");
            bookRepository.delete(book);
            return true;
        }
        logger.debug("The book has not been deleted (book == null)");
        return false;
    }

    public Double buyBooks(User user) {
        double sum = 0;
        if (user != null) {
            logger.debug("The book has been successfully purchased");
            List<Book> books = user.getBooks();
            for (Book value : books) {
                sum = (value.getCost() * value.getAmount()) + sum;
            }
            return sum;
        }
        logger.debug("The book was not purchased (user ==null)");
        return sum;
    }
}
