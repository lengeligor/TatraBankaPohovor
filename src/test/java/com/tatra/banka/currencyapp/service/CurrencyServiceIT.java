package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.dto.CurrencyDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CurrencyServiceIT {

    private final CurrencyService service;

    @Autowired
    public CurrencyServiceIT(CurrencyService service){
        this.service = service;
    }


    @Test
    void getExchangeRateList(){
        List<CurrencyDto> result = service.getExchangeRateList();

        assertEquals(3, result.size());
    }

    @Test
    void convertWithFromCurrencyNotFound(){
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.convert("USD", "CZK", 1.0);
        });
        assertEquals("Currency with code USD is not recognized!", exception.getMessage());
    }

    @Test
    void convertWithToCurrencyNotFound(){
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.convert("CHF", "TRY", 1.0);
        });
        assertEquals("Currency with code TRY is not recognized!", exception.getMessage());
    }
}
