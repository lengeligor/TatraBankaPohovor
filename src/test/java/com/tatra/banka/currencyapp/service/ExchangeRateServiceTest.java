package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.ObjectFactory;
import com.tatra.banka.currencyapp.entity.Currency;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRateServiceTest {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService();

    @Test
    void convert(){
        Map<Double, List<Currency>> expectedResults = ObjectFactory.createExpectedResults();

        assertNotNull(expectedResults);
        assertFalse(expectedResults.isEmpty());
        expectedResults.forEach(
                (key, value) -> assertEquals(key, exchangeRateService.convert(value.get(0), value.get(1), 1.0), 0.0001)
        );
    }
}
