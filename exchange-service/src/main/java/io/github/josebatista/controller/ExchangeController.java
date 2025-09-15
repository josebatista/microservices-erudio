package io.github.josebatista.controller;

import io.github.josebatista.environment.InstanceInformationService;
import io.github.josebatista.model.Exchange;
import io.github.josebatista.repository.ExchangeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(name = "Exchange Endpoint")
@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

    @Autowired
    private InstanceInformationService informationService;

    @Autowired
    private ExchangeRepository exchangeRepository;

    // http://localhost:8000/exchange-service/5/USD/BRL
    @Operation(summary = "Get an exchange from amount of currency!")
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to
    ) {
        Exchange exchange = exchangeRepository.findByFromAndTo(from, to);
        if (exchange == null) throw new RuntimeException("Currency Unsupported!");
        BigDecimal convertedValue = exchange.getConversionFactor().multiply(amount);
        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("PORT: " + informationService.retrieveServerPort());
        return exchange;
    }

}
