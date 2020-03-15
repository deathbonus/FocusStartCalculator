package com.deathbonus.focusstartcalculator.calculator;

import java.util.ArrayList;

public class StackOfChars implements Stack<Character>{
    private ArrayList<Character> elements;

    public StackOfChars() {
        elements = new ArrayList<Character>();
    }

    @Override
    public void push(Character element) {
        elements.add(element);
    }

    @Override
    public Character pop() {
        Character character = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);
        return character;
    }

    @Override
    public Character peek() {
        return elements.get(elements.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}