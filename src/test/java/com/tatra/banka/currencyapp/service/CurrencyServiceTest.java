package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.ObjectFactory;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.entity.Currency;
import com.tatra.banka.currencyapp.mapper.CurrencyMapper;
import com.tatra.banka.currencyapp.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest {

    private final CurrencyRepository repository = Mockito.mock(CurrencyRepository.class);
    private final CurrencyMapper mapper = Mockito.mock(CurrencyMapper.class);
    private final ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);

    private final CurrencyService service = new CurrencyService(repository, mapper, exchangeRateService);


    @Test
    void getExchangeRateList(){
        Currency currency = ObjectFactory.createCurrency();
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();
        Page<Currency> mockList = new PageImpl<>(List.of(currency));

        when(repository.findAll(PageRequest.of(0, 10))).thenReturn(mockList);
        when(mapper.toCurrencyDto(currency)).thenReturn(currencyDto);

        Page<CurrencyDto> result = service.getExchangeRateList(PageRequest.of(0, 10));

        assertEquals(1, result.getTotalElements());
        assertEquals(currencyDto.getCountry(), result.getContent().get(0).getCountry());
    }

    @Test
    void convertWithFromCurrencyNotFound(){
        Currency currencyTo = ObjectFactory.createCurrency();
        when(repository.findByCode("CHF")).thenReturn(Optional.empty());
        when(repository.findByCode("CZK")).thenReturn(Optional.of(currencyTo));

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.convert("CHF", "CZK", 1.0);
        });
        assertEquals("Currency with code CHF is not recognized!", exception.getMessage());
        verify(repository).findByCode("CHF");
        verify(repository, never()).findByCode("CZK");
    }

    @Test
    void convertWithToCurrencyNotFound(){
        Currency currencyFrom = ObjectFactory.createCurrency();
        when(repository.findByCode("CHF")).thenReturn(Optional.of(currencyFrom));
        when(repository.findByCode("CZK")).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.convert("CHF", "CZK", 1.0);
        });
        assertEquals("Currency with code CZK is not recognized!", exception.getMessage());
        verify(repository).findByCode("CHF");
        verify(repository).findByCode("CZK");
    }

    @Test
    void convertSuccess(){
        Currency currencyFrom = ObjectFactory.createCurrency();
        Currency currencyTo = ObjectFactory.createCurrency();
        //TODO
    }
}
