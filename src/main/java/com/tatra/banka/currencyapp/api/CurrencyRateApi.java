package com.tatra.banka.currencyapp.api;

import com.tatra.banka.currencyapp.dto.CurrencyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CurrencyRateApi {

    ResponseEntity<Page<CurrencyDto>> getExchangeRateList(Pageable pageable);
}
