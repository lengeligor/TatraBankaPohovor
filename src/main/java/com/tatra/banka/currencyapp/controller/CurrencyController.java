package com.tatra.banka.currencyapp.controller;

import com.tatra.banka.currencyapp.api.CurrencyRateApi;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.service.CurrencyService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController implements CurrencyRateApi {

    private final CurrencyService currencyService;

    public CurrencyController(@NonNull CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<CurrencyDto>> getExchangeRateList() {
        return ResponseEntity.ok(currencyService.getExchangeRateList());
    }

    @Override
    @GetMapping("/convert")
    public ResponseEntity<Double> convert(@RequestParam String fromCurrencyCode, @RequestParam String toCurrencyCode, @RequestParam Double value) {
        return ResponseEntity.ok(currencyService.convert(fromCurrencyCode, toCurrencyCode, value));
    }
}
