package com.tatra.banka.currencyapp.api;

import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CurrencyRateApi {

    ResponseEntity<Long> createCurrency(CurrencyDto currencyDto) throws BusinessException;

    ResponseEntity<CurrencyDto> getCurrency(Long id);

    ResponseEntity<Void> deleteCurrency(Long id);

    ResponseEntity<List<CurrencyDto>> getExchangeRateList();

    ResponseEntity<Double> convert(String fromCurrencyCode, String toCurrencyCode, Double value);
}
