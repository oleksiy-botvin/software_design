package data.entity.money;

import java.util.Currency;

public final class USD extends Money {

    public USD() {
        super(Currency.getInstance("USD"));
    }

    public USD(float value) {
        super(Currency.getInstance("USD"), value);
    }

    public USD(int value) {
        super(Currency.getInstance("USD"), value);
    }

    public USD(int value, int cents) {
        super(Currency.getInstance("USD"), value, cents);
    }
}
