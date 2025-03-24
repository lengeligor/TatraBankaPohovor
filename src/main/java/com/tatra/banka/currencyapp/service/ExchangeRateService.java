package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.entity.Currency;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    public ExchangeRateService(){
    }

    public double convert(Currency fromCurrency, Currency toCurrency, Double value) {
        double fromCurrencyRateToEUR = fromCurrency.getForeignExchangePurchase();
        double toCurrencyRateToEUR = toCurrency.getForeignExchangePurchase();
        double valueInEur = value / fromCurrencyRateToEUR;
        return valueInEur * toCurrencyRateToEUR;
    }
}
