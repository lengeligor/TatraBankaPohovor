package com.tatra.banka.currencyapp.dto;

import lombok.Data;

@Data
public class CurrencyDto {

    private Long id;
    private String country;
    private String code;
    private Double foreignExchangePurchase;
    private Double foreignExchangeSales;
    private Double foreignExchangeCenter;
    private Double currencyPurchase;
    private Double currencySales;
    private Double currencyCenter;
}
