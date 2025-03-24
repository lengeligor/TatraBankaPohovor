package com.tatra.banka.currencyapp.controller;

import com.tatra.banka.currencyapp.api.CurrencyRateApi;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.service.CurrencyService;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController implements CurrencyRateApi {

    private final CurrencyService currencyService;

    public CurrencyController(@NonNull CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<Page<CurrencyDto>> getExchangeRateList(Pageable pageable) {
        return ResponseEntity.ok(currencyService.getExchangeRateList(pageable));
    }
}
