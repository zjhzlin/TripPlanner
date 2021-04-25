package com.lynn;

public class Money {

    private double amount;
    private Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isLargerThan(Money other, ForexConverter fc) {
        // if this money larger than other money (converting to the same currency), return true
        // convert other to this money's currency
        Money otherInThisCurrency = fc.convert(other, this.getCurrency());
        if (this.getAmount() >= otherInThisCurrency.getAmount()) {
            return true;
        }
        else {
            return false;
        }
    }
    // isLargerThanWithinThreshold - too 2

    // isCloseTo
    public boolean isCloseTo(Money other, ForexConverter fc, double threshold) {
        Money otherInThisCurrency = fc.convert(other, this.getCurrency());
        if (Math.abs(this.getAmount() - otherInThisCurrency.getAmount()) <= Math.abs(threshold)) {
            return true;
        }
        else {
            return false;
        }
    }


//    @Override
//    public int compareTo(Money other) {
//        // this > other, return pos int
//        // this < other, return neg int
//        // = return 0
//        // convert all to this money's currency
//        ForexConverter fc = new ForexConverter();   // shouldn't be here. better to import it as parameter
//        Money otherInThisCurrency = fc.convert(other, this.getCurrency());
//        if (this.getAmount() > otherInThisCurrency.getAmount()) {
//            return 1;
//        }
//        else if (this.getAmount() < otherInThisCurrency.getAmount()) {
//            return -1;
//        }
//        else {
//            return 0;
//        }
//
//    }
}
