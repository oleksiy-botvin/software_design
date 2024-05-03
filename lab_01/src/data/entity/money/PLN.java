package data.entity.money;

import java.util.Currency;

public final class PLN extends Money {

    public PLN() {
        super(Currency.getInstance("PLN"));
    }

    public PLN(float value) {
        super(Currency.getInstance("PLN"), value);
    }

    public PLN(int value) {
        super(Currency.getInstance("PLN"), value);
    }

    public PLN(int value, int cents) {
        super(Currency.getInstance("PLN"), value, cents);
    }

    @Override
    public PLN copy() {
        return new PLN(this.getValue(), this.getCents());
    }
}
