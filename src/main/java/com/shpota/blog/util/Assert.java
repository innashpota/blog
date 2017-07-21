package com.shpota.blog.util;

public class Assert {
    public static void notEmpty(String str, String message) {
        if ("".equals(str) || str == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isPositive(int number, String message) {
        if (number <= 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}