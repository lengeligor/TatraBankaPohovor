package com.tatra.banka.currencyapp;

import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.entity.Currency;

public class ObjectFactory {

    public static CurrencyDto createCurrencyDto(){
        CurrencyDto currencyDto =  new CurrencyDto();
        currencyDto.setCountry("Česká republika");
        currencyDto.setCode("CZK");
        currencyDto.setForeignExchangePurchase(25.5750);
        currencyDto.setForeignExchangeSales(24.3200);
        currencyDto.setForeignExchangeCenter(24.9480);
        currencyDto.setCurrencyPurchase(25.8240);
        currencyDto.setCurrencySales(24.0710);
        currencyDto.setCurrencyCenter(24.9480);
        return currencyDto;
    }

    public static Currency createCurrency(){
        Currency currency =  new Currency();
        currency.setCountry("Česká republika");
        currency.setCode("CZK");
        currency.setForeignExchangePurchase(25.5750);
        currency.setForeignExchangeSales(24.3200);
        currency.setForeignExchangeCenter(24.9480);
        currency.setCurrencyPurchase(25.8240);
        currency.setCurrencySales(24.0710);
        currency.setCurrencyCenter(24.9480);
        return currency;
    }
}
