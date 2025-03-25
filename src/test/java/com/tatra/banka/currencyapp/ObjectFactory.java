package com.tatra.banka.currencyapp;

import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.entity.Currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Currency createCurrency(String country, String code, double foreignExchangePurchase){
        Currency currency =  new Currency();
        currency.setCountry(country);
        currency.setCode(code);
        currency.setForeignExchangePurchase(foreignExchangePurchase);
        return currency;
    }

    public static Map<Double, List<Currency>> createExpectedResults() {
        Map<Double, List<Currency>> expectedResults = new HashMap<>();
        Currency czk = createCurrency("Česká republika", "CZK", 25.5750);
        Currency dkk = createCurrency("Dánsko","DKK",7.6461);
        Currency chf = createCurrency("Švajčiarsko","CHF",0.9782);
        expectedResults.put(0.2989, List.of(czk,dkk));
        expectedResults.put(0.0382, List.of(czk,chf));
        expectedResults.put(3.3448, List.of(dkk,czk));
        expectedResults.put(0.1279, List.of(dkk,chf));
        expectedResults.put(26.1449, List.of(chf,czk));
        expectedResults.put(7.8164, List.of(chf,dkk));
        return expectedResults;
    }
}
