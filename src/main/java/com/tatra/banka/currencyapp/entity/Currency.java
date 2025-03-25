package com.tatra.banka.currencyapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @Column(name = "krajina", nullable = false)
    private String country;

    @Column(name = "kod", nullable = false, unique = true)
    private String code;

    @Column(name = "deviza_nakup", nullable = false)
    private Double foreignExchangePurchase;

    @Column(name = "deviza_predaj", nullable = false)
    private Double foreignExchangeSales;

    @Column(name = "deviza_stred", nullable = false)
    private Double foreignExchangeCenter;

    @Column(name = "valuta_nakup")
    private Double currencyPurchase;

    @Column(name = "valuta_predaj")
    private Double currencySales;

    @Column(name = "valuta_stred")
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
