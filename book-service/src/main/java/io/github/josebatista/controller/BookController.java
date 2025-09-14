package io.github.josebatista.controller;

import io.github.josebatista.environment.InstanceInformationService;
import io.github.josebatista.model.Book;
import io.github.josebatista.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-service")
public class BookController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private BookRepository repository;

    // http://localhost:8100/book-service/1/BRL
    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency
    ) {
        Book book = repository.findById(id).orElseThrow();

        book.setCurrency(currency);
        book.setEnvironment("PORT: " + informationService.retrieveServerPort());
        return book;
    }

}
