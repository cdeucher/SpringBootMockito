package com.app.product.main.common;

import java.util.*;

public class ArrayTests {

    ArrayList cars;
    List shoes;
    Set panties;
    LinkedList arms;

    public ArrayTests() {
        cars = new ArrayList<>();
        shoes = new ArrayList<>();
        panties = new HashSet<>();
        arms = new LinkedList<>();

        cars.add("1111");
        shoes.add("1212");
        panties.add("121212");
        arms.add("asasas");

        arms.getFirst();

    }
}
