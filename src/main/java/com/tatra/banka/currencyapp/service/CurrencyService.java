package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.entity.Currency;
import com.tatra.banka.currencyapp.mapper.CurrencyMapper;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final ExchangeRateService exchangeRateService;

    public CurrencyService(@NonNull CurrencyRepository currencyRepository, @NonNull CurrencyMapper currencyMapper,
                           @NonNull ExchangeRateService exchangeRateService) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.exchangeRateService = exchangeRateService;
    }

    public Page<CurrencyDto> getExchangeRateList(Pageable pageable) {
        return currencyRepository.findAll(pageable).map(currencyMapper::toCurrencyDto);
    }

    public Double convert(String fromCurrencyCode, String toCurrencyCode, Double value) {
        Currency fromCurrency = currencyRepository.findByCode(fromCurrencyCode).orElse(null);
        if (fromCurrency == null) {
            throw new EntityNotFoundException(String.format("Currency with code %s is not recognized!", fromCurrencyCode));
        }
        Currency toCurrency = currencyRepository.findByCode(toCurrencyCode).orElse(null);
        if (toCurrency == null) {
            throw new EntityNotFoundException(String.format("Currency with code %s is not recognized!", toCurrencyCode));
        }
        return exchangeRateService.convert(fromCurrency, toCurrency, value);
    }
}
