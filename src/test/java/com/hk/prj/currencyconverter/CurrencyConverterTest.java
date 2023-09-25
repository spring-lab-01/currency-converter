package com.hk.prj.currencyconverter;

import com.hk.prj.currencyconverter.currency.CurrencyConverterController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CurrencyConverterController.class)
public class CurrencyConverterTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void convertTest_success() throws Exception {
        mockMvc.perform(get("/currency-converter")).andExpect(status().is2xxSuccessful());
    }


}
