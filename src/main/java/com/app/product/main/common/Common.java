package com.app.product.main.common;

public class Common {

    public static int generateRandomId() {
        return (int) ((Math.random() * (99999 - 1)) + 1);
    }
}