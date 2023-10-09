class Money {
    Integer amount
    String currency

    Money plus(Money other) {
        if (this.currency != other.currency) throw new IllegalArgumentException('Cannot add different currencies');
        new Money(amount: this.amount + other.amount, currency: this.currency)
    }

    public String toString() {
        "${amount} ${currency}"
    }
}

class MoneyCategory {
    static Money getEur(Integer value) {
        return new Money(amount: value, currency: "EUR");
    }

    static Money getUsd(Integer value) {
        return new Money(amount: value, currency: "USD");
    }
//TASK Define methods of the MoneyCategory class so that the code below passes
}

use(MoneyCategory) {
    println 10.eur
    println 10.eur + 20.eur
    println 10.usd + 20.usd
}