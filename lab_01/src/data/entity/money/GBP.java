package data.entity.money;

import java.util.Currency;

public final class GBP extends Money {

    public GBP() {
        super(Currency.getInstance("GBP"));
    }

    public GBP(float value) {
        super(Currency.getInstance("GBP"), value);
    }

    public GBP(int value) {
        super(Currency.getInstance("GBP"), value);
    }

    public GBP(int value, int cents) {
        super(Currency.getInstance("GBP"), value, cents);
    }

    @Override
    public GBP copy() {
        return new GBP(this.getValue(), this.getCents());
    }
}
