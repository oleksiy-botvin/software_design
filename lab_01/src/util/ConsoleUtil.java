package util;

import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtil {

    public static int selectItem(@NotNull String title, @NotNull String... items) {
        return selectItem(title, true, items);
    }

    public static int selectItem(@NotNull String title, boolean isFirstAtEnd, @NotNull String... items) {
        if (items.length == 0) return -1;

        System.out.println("\n" + title + " та натисніть Enter");

        for (int i = isFirstAtEnd ? 1 : 0; i < items.length; i++) System.out.println("\t" + i + " -> " + items[i]);
        if (isFirstAtEnd) System.out.println("\t" + 0 + " -> " + items[0]);

        try {
           int value = new Scanner(System.in).nextInt();
           if (value < 0 || value >= items.length) throw new Throwable();
           return value;
        } catch (Throwable e) {
            return selectItem(title, isFirstAtEnd, items);
        }
    }

    public static void inputEnter() {
        System.out.print("Щоб продовжити, натисніть Enter");
        try {
            new Scanner(System.in).nextLine();
        } catch (Throwable ignored) {
        }
    }

    public static @NotNull String inputLine(@NotNull String message) {
        System.out.print(message + ": ");
        String error;
        try {
            String value = new Scanner(System.in).nextLine();
            if (value.isEmpty()) throw new InputMismatchException();
            return value;
        } catch (InputMismatchException e) {
            error = "Невірний формат вводу";
        } catch (Throwable t) {
            error = "Сталася непередбачена помилка";
        }
        System.out.println("\n" + error +  ". Повторіть спробу!\n");
        return inputLine(message);
    }

    public static int inputInt(@NotNull String message) {
        return inputInt(message, 1, Integer.MAX_VALUE);
    }

    public static int inputInt(@NotNull String message, int min, int max) {
        System.out.print(message + ": ");
        String error;
        try {
            int value = new Scanner(System.in).nextInt();
            if (value >= min && value <= max) return value;
            throw new NumberFormatException();
        } catch (NumberFormatException e) {
            error = "Введіть число від " + min + " до " + max;
        } catch (InputMismatchException e) {
            error = "Невірний формат вводу";
        } catch (Throwable t) {
            error = "Сталася непередбачена помилка";
        }
        System.out.println("\n" + error +  ". Повторіть спробу!\n");
        return inputInt(message);
    }

    public static float inputFloat(@NotNull String message) {
        System.out.print(message + ": ");
        String error;
        try {
            float value = new Scanner(System.in).nextFloat();
            if (value > 0) return value;
            throw new InputMismatchException();
        } catch (InputMismatchException e) {
            error = "Невірний формат вводу";
        } catch (Throwable t) {
            error = "Сталася непередбачена помилка";
        }
        System.out.println("\n" + error +  ". Повторіть спробу!\n");
        return inputFloat(message);
    }
}
