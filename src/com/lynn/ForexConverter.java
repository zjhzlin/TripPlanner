package com.lynn;

import java.util.HashMap;

public class ForexConverter {

    // convert money
    // all convert to USD, use USD as agent

    private HashMap<Currency, Double> currencyRates = new HashMap<>();

    public ForexConverter() {

        currencyRates.put(Currency.GBP, 0.72);  // GBP per USD
        currencyRates.put(Currency.EURO, 0.83);  // EURO per USD

    }


    public Money convert(Money fromMoney, Currency toCurrency) {
        Currency fromCurrency = fromMoney.getCurrency();
        double toAmount = fromMoney.getAmount() * currencyRates.get(toCurrency) / currencyRates.get(fromCurrency);

        return new Money(toAmount, toCurrency);
    }

}
