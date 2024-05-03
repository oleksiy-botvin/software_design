package data.warehouse;

import data.entity.Reporting;
import data.entity.events.Event;
import org.jetbrains.annotations.NotNull;
import util.function.NotNullConsumer;

public interface Warehouse {

    default void event(@NotNull Event event, @NotNull NotNullConsumer<Reporting> onSuccess) {
        event(event, onSuccess, it -> {});
    }
    void event(
            @NotNull Event event,
            @NotNull NotNullConsumer<Reporting> onSuccess,
            @NotNull NotNullConsumer<Throwable> onError
    );
}
