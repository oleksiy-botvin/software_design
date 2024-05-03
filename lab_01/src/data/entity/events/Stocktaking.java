package data.entity.events;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class Stocktaking extends Event {

    public Stocktaking() {
        super();
    }

    public Stocktaking(@NotNull Calendar date) {
        super(date);
    }
}
