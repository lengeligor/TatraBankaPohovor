package com.tatra.banka.currencyapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;

    @Column(name = "krajina", nullable = false)
    private String country;

    @Column(name = "kod", nullable = false)
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

}
