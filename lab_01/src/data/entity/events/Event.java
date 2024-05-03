package data.entity.events;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public abstract class Event {

    @NotNull private final Calendar date;

    public Event() {
        this(Calendar.getInstance());
    }

    public Event(@NotNull Calendar date) {
        this.date = date;
    }

    public @NotNull Calendar getDate() {
        return date;
    }
}
