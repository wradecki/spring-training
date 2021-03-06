package pl.training.shop.commons.money;

import org.javamoney.moneta.FastMoney;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.Locale;

public class LocalMoney {

    public static FastMoney of(Number number) {
        return FastMoney.of(number, getDefaultCurrency());
    }

    public static FastMoney zero() {
        return FastMoney.zero(getDefaultCurrency());
    }

    private static CurrencyUnit getDefaultCurrency() {
        var locale = Locale.getDefault();
        return Monetary.getCurrency(locale);
    }

}
