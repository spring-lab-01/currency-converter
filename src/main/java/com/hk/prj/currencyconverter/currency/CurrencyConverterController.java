package com.hk.prj.currencyconverter.currency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-converter")
public class CurrencyConverterController {

    @GetMapping
    public Long convert(Currency fromCurrency, Currency toCurrency){
        return 20L;
    }
}
