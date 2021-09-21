package pl.training.shop.payments;

import org.javamoney.moneta.FastMoney;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

public class LocalMoney {

    public static FastMoney of(Number number) {
        var locale = Locale.getDefault();
        var currencyUnit = Monetary.getCurrency(locale);
        return FastMoney.of(number, currencyUnit);
    }

    public static FastMoney zero() {
        return of(0);
    }

}
