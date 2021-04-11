package com.example;

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
