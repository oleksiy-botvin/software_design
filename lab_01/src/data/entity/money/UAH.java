package data.entity.money;

import org.jetbrains.annotations.NotNull;

import java.util.Currency;

public final class UAH extends Money {

    public UAH() {
        super(Currency.getInstance("UAH"));
    }

    public UAH(float value) {
        super(Currency.getInstance("UAH"), value);
    }

    public UAH(int value) {
        super(Currency.getInstance("UAH"), value);
    }

    public UAH(int value, int cents) {
        super(Currency.getInstance("UAH"), value, cents);
    }

    @Override
    public UAH copy() {
        return new UAH(this.getValue(), this.getCents());
    }
}
