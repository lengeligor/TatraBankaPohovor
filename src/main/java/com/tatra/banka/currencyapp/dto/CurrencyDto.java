package com.tatra.banka.currencyapp.dto;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getForeignExchangePurchase() {
        return foreignExchangePurchase;
    }

    public void setForeignExchangePurchase(Double foreignExchangePurchase) {
        this.foreignExchangePurchase = foreignExchangePurchase;
    }

    public Double getForeignExchangeSales() {
        return foreignExchangeSales;
    }

    public void setForeignExchangeSales(Double foreignExchangeSales) {
        this.foreignExchangeSales = foreignExchangeSales;
    }

    public Double getForeignExchangeCenter() {
        return foreignExchangeCenter;
    }

    public void setForeignExchangeCenter(Double foreignExchangeCenter) {
        this.foreignExchangeCenter = foreignExchangeCenter;
    }

    public Double getCurrencyPurchase() {
        return currencyPurchase;
    }

    public void setCurrencyPurchase(Double currencyPurchase) {
        this.currencyPurchase = currencyPurchase;
    }

    public Double getCurrencySales() {
        return currencySales;
    }

    public void setCurrencySales(Double currencySales) {
        this.currencySales = currencySales;
    }

    public Double getCurrencyCenter() {
        return currencyCenter;
    }

    public void setCurrencyCenter(Double currencyCenter) {
        this.currencyCenter = currencyCenter;
    }
}
