package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.ObjectFactory;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.entity.Currency;
import com.tatra.banka.currencyapp.mapper.CurrencyMapper;
import com.tatra.banka.currencyapp.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyServiceTest {

    private final CurrencyRepository repository = Mockito.mock(CurrencyRepository.class);
    private final CurrencyMapper mapper = Mockito.mock(CurrencyMapper.class);
    private final ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);

    private final CurrencyService service = new CurrencyService(repository, mapper, exchangeRateService);

    @Test
    void createCurrency(){
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();
        currencyDto.setId(1L);
        Currency currency = ObjectFactory.createCurrency();
        Currency savedCurrency = ObjectFactory.createCurrency();
        savedCurrency.setId(1L);
        when(mapper.toCurrency(currencyDto)).thenReturn(currency);
        when(repository.save(currency)).thenReturn(savedCurrency);

        Long currencyId;
        try {
            currencyId = service.createCurrency(currencyDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertEquals(1L, currencyId);
        verify(mapper).toCurrency(currencyDto);
        verify(repository).save(currency);
    }

    @Test
    void getCurrency(){
        Currency currency = ObjectFactory.createCurrency();
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();
        when(repository.findById(1L)).thenReturn(Optional.of(currency));
        when(mapper.toCurrencyDto(currency)).thenReturn(currencyDto);

        CurrencyDto result = service.getCurrency(1L);
        assertEquals(currencyDto.getCode(), result.getCode());
        assertEquals(currencyDto.getCountry(), result.getCountry());
    }

    @Test
    void deleteCurrency(){
        doNothing().when(repository).deleteById(1L);
        service.delete(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void getExchangeRateList(){
        Currency currency = ObjectFactory.createCurrency();
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();
        List<Currency> mockList = List.of(currency);

        when(repository.findAll()).thenReturn(mockList);
        when(mapper.toCurrencyDto(currency)).thenReturn(currencyDto);

        List<CurrencyDto> result = service.getExchangeRateList();

        assertEquals(1, result.size());
        assertEquals(currencyDto.getCountry(), result.get(0).getCountry());
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
        Currency currencyFrom = ObjectFactory.createCurrency("Česká republika", "CZK", 25.5750);
        Currency currencyTo = ObjectFactory.createCurrency("Dánsko","DKK",7.6461);

        when(repository.findByCode("CZK")).thenReturn(Optional.of(currencyFrom));
        when(repository.findByCode("DKK")).thenReturn(Optional.of(currencyTo));
        when(exchangeRateService.convert(currencyFrom,currencyTo,1.0)).thenReturn(0.2989);

        assertEquals(0.2989, service.convert("CZK", "DKK", 1.0));

        verify(repository).findByCode("DKK");
        verify(repository).findByCode("CZK");
        verify(exchangeRateService).convert(currencyFrom,currencyTo,1.0);
    }
}
