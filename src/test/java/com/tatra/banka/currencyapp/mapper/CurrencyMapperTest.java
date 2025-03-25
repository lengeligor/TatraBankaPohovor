package com.tatra.banka.currencyapp.mapper;

import com.tatra.banka.currencyapp.ObjectFactory;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.entity.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyMapperTest {

    private final CurrencyMapper mapper = new CurrencyMapperImpl();

    @Test
    void toCurrencyDto(){
        Currency currency = ObjectFactory.createCurrency();
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();

        CurrencyDto result = mapper.toCurrencyDto(currency);

        assertEquals(currencyDto.getCountry(), result.getCountry());
        assertEquals(currencyDto.getCode(), result.getCode());
        assertEquals(currencyDto.getForeignExchangePurchase(), result.getForeignExchangePurchase());
        assertEquals(currencyDto.getForeignExchangeSales(), result.getForeignExchangeSales());
        assertEquals(currencyDto.getForeignExchangeCenter(), result.getForeignExchangeCenter());
        assertEquals(currencyDto.getCurrencyPurchase(), result.getCurrencyPurchase());
        assertEquals(currencyDto.getCurrencySales(), result.getCurrencySales());
        assertEquals(currencyDto.getCurrencyCenter(), result.getCurrencyCenter());
    }

    @Test
    void toCurrency(){
        Currency currency = ObjectFactory.createCurrency();
        CurrencyDto currencyDto = ObjectFactory.createCurrencyDto();

        Currency result = mapper.toCurrency(currencyDto);

        assertEquals(currency.getCountry(), result.getCountry());
        assertEquals(currency.getCode(), result.getCode());
        assertEquals(currency.getForeignExchangePurchase(), result.getForeignExchangePurchase());
        assertEquals(currency.getForeignExchangeSales(), result.getForeignExchangeSales());
        assertEquals(currency.getForeignExchangeCenter(), result.getForeignExchangeCenter());
        assertEquals(currency.getCurrencyPurchase(), result.getCurrencyPurchase());
        assertEquals(currency.getCurrencySales(), result.getCurrencySales());
        assertEquals(currency.getCurrencyCenter(), result.getCurrencyCenter());
    }
}
