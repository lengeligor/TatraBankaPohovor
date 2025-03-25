package com.tatra.banka.currencyapp.api;

import com.tatra.banka.currencyapp.dto.CurrencyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CurrencyRateApi {

    ResponseEntity<List<CurrencyDto>> getExchangeRateList();

    ResponseEntity<Double> convert(String fromCurrencyCode, String toCurrencyCode, Double value);
}
