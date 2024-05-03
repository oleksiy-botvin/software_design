package data.entity.money;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class Money {

    @NotNull private final Currency currency;
    private int value;
    private int cents;

    public Money(@NotNull Currency currency) {
        this(currency, 0);
    }

    public Money(@NotNull Currency currency, float value) {
        this(currency, (int) value, (int) (((value - ((int) value)) * 100)));
    }

    public Money(@NotNull Currency currency, int value) {
        this(currency, value, 0);
    }

    public Money(@NotNull Currency currency, int value, int cents) {
        this.currency = currency;
        setValue(value);
        setCents(cents);
    }

    public @NotNull Currency getCurrency() {
        return currency;
    }

    public int getValue() {
        return value;
    }

    public void setValue(float value) {
        setValue((int) value);
        setCents((int) (((value - ((int) value)) * 100)));
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        int it = cents / 100;
        setValue(getValue() + it);
        this.cents = cents - it;
    }

    public float toFloat() {
        return getValue() + (getCents() / 100f);
    }

    public void print() {
        System.out.println(this);
    }

    public Money copy() {
        return new Money(currency, value, cents);
    }

    @Override
    public String toString() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("uk", "UA"));
        format.setCurrency(currency);
        format.setMaximumFractionDigits(2);
        return format.format(getValue() + (getCents() / 100f));
    }
}
