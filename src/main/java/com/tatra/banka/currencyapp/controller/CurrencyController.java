package com.tatra.banka.currencyapp.controller;

import com.tatra.banka.currencyapp.api.CurrencyRateApi;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.exceptions.BusinessException;
import com.tatra.banka.currencyapp.service.CurrencyService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController implements CurrencyRateApi {

    private final CurrencyService currencyService;

    public CurrencyController(@NonNull CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Long> createCurrency(@RequestBody CurrencyDto currencyDto) throws BusinessException {
        return ResponseEntity.ok(currencyService.createCurrency(currencyDto));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable Long id) {
        return ResponseEntity.ok(currencyService.getCurrency(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        currencyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
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
