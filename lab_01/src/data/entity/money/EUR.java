package data.entity.money;

import java.util.Currency;

public final class EUR extends Money {

    public EUR() {
        super(Currency.getInstance("EUR"));
    }

    public EUR(float value) {
        super(Currency.getInstance("EUR"), value);
    }

    public EUR(int value) {
        super(Currency.getInstance("EUR"), value);
    }

    public EUR(int value, int cents) {
        super(Currency.getInstance("EUR"), value, cents);
    }

    @Override
    public EUR copy() {
        return new EUR(this.getValue(), this.getCents());
    }

}
