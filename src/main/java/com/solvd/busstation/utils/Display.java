package com.solvd.busstation.utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Display {
    public static void printList(List<?> objects) {
        AtomicInteger i = new AtomicInteger(0);
        objects.stream().forEach(iter -> {
            System.out.println(i.incrementAndGet() + "." + iter);
        });
    }
}
