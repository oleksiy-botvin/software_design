package util.function;

import org.jetbrains.annotations.NotNull;

public interface NotNullConsumer<T> {
    void accept(@NotNull T it);

}
