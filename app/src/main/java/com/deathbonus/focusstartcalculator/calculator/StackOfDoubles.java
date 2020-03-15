package com.deathbonus.focusstartcalculator.calculator;

import java.util.ArrayList;

public class StackOfDoubles implements Stack<Double>{
    private ArrayList<Double> elements;

    public StackOfDoubles() {
        elements = new ArrayList<Double>();
    }

    @Override
    public void push(Double element) {
        elements.add(element);
    }

    @Override
    public Double pop() {
        double d = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);
        return d;
    }

    @Override
    public Double peek() {
        return elements.get(elements.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}