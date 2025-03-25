package com.tatra.banka.currencyapp.service;

import com.tatra.banka.currencyapp.entity.Currency;
import com.tatra.banka.currencyapp.exceptions.BusinessException;
import com.tatra.banka.currencyapp.mapper.CurrencyMapper;
import com.tatra.banka.currencyapp.dto.CurrencyDto;
import com.tatra.banka.currencyapp.repository.CurrencyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Long createCurrency(CurrencyDto currencyDto) throws BusinessException {
        if (currencyDto == null || currencyDto.getCode() == null){
            throw new BusinessException("Code is required!");
        }
        Currency currency = currencyMapper.toCurrency(currencyDto);
        return currencyRepository.save(currency).getId();
    }

    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }

    public CurrencyDto getCurrency(Long id) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Currency with id %d is not recognized!", id)));
        return currencyMapper.toCurrencyDto(currency);
    }

    public List<CurrencyDto> getExchangeRateList() {
        return currencyRepository.findAll().stream().map(currencyMapper::toCurrencyDto).toList();
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
