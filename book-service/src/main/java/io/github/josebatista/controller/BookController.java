package io.github.josebatista.controller;

import io.github.josebatista.dto.ExchangeDto;
import io.github.josebatista.environment.InstanceInformationService;
import io.github.josebatista.model.Book;
import io.github.josebatista.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

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
        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);
        ResponseEntity<ExchangeDto> response = new RestTemplate()
                .getForEntity(
                        "http://localhost:8000/exchange-service/{amount}/{from}/{to}",
                        ExchangeDto.class,
                        params
                );
        ExchangeDto exchange = response.getBody();
        book.setPrice(exchange.getConvertedValue());
        book.setCurrency(currency);
        book.setEnvironment("PORT: " + informationService.retrieveServerPort());
        return book;
    }

}
