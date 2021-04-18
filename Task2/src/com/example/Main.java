package com.example;

/* Условие задания:
    У вас есть Java функция в которую пользователь передает текст и регулярное выражение.
    Измените функцию так, чтобы избежать зависаний и выбрасывания исключений в процессе исполнения.

    public boolean matches(String text, String regex) {
        return Pattern.compile(regex).matcher(text).matches();
    }

    Подсказка: вы не хотите вечно ждать пока matches() закончит работу.
 */

import java.util.concurrent.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {  }

    public boolean matches(String text, String regex) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Callable<Boolean> callable = () -> Pattern.compile(regex).matcher(text).matches();
            Future<Boolean> future = executor.submit(callable);
            // ждём результата в течении 3-х секунд,
            // иначе выходим из функции и возвращаем false
            return future.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            return false;
        }
    }
}
