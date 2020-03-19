package com.deathbonus.focusstartcalculator.calculator;

public class Calculator {
    private Stack stackOfChars;
    private Stack stackOfDoubles;
    private StringBuilder postfixExpression;


    public String calculate(String input) {
        stackOfChars = new StackOfChars();
        postfixExpression = new StringBuilder();
        transformToPostfixExpression(input);
        return calculatePostfixExpression(postfixExpression.toString());
    }

    private void transformToPostfixExpression(String infixExpression) {
        for (int i = 0; i < infixExpression.length(); i++) {
            char symbol = infixExpression.charAt(i);
            switch (symbol) {
                case '+':
                case '-':
                case '*':
                case '/':
                    checkOperator(symbol);
                    break;
                case '(':
                    stackOfChars.push(symbol);
                    break;
                case ')':
                    while (!stackOfChars.isEmpty()) {
                        if ((char) stackOfChars.peek() == '(') {
                            stackOfChars.pop();
                            break;
                        } else {
                            postfixExpression.append("_").append(stackOfChars.pop());
                        }
                    }
                    break;
                default:
                    if (i != 0) {
                        checkOperand(symbol, infixExpression.charAt(i - 1));
                    } else {
                    postfixExpression.append(symbol);
                    }
                    break;
            }
        }
        while (!stackOfChars.isEmpty()) {
            postfixExpression.append("_").append(stackOfChars.pop());
        }
    }

    private void checkOperator(char symbol) {
        if (!stackOfChars.isEmpty()) {
            switch ((char) stackOfChars.peek()) {
                case '*':
                case '/':
                    if (symbol == '+' || symbol == '-') {
                        postfixExpression.append("_").append(stackOfChars.pop());
                        stackOfChars.push(symbol);
                        break;
                    }
                case '+':
                case '-':
                    stackOfChars.push(symbol);
                    break;
                default:
                    stackOfChars.push(symbol);
                    break;
            }
        } else {
            stackOfChars.push(symbol);
        }
    }

    private void checkOperand(char symbol, char previousSymbol) {
            switch (previousSymbol) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '(':
                    postfixExpression.append("_");
                default:
                    postfixExpression.append(symbol);
                    break;
            }
    }

    private String calculatePostfixExpression(String postfixExpression) {
        String[] postfixExpressionArray = postfixExpression.split("_");
        stackOfDoubles = new StackOfDoubles();
        double result = 0;
        double last;
        double previous;
        for (int i = 0; i < postfixExpressionArray.length; i++) {
            String symbol = postfixExpressionArray[i];
            switch (symbol) {
                case "+":
                    last = (double) stackOfDoubles.pop();
                    previous = stackOfDoubles.isEmpty() ? result : (double) stackOfDoubles.pop();
                    result = previous + last;
                    stackOfDoubles.push(result);
                    break;
                case "-":
                    last = (double) stackOfDoubles.pop();
                    previous = stackOfDoubles.isEmpty() ? result : (double) stackOfDoubles.pop();
                    result = previous - last;
                    stackOfDoubles.push(result);
                    break;
                case "*":
                    last = (double) stackOfDoubles.pop();
                    previous = stackOfDoubles.isEmpty() ? result : (double) stackOfDoubles.pop();
                    result = previous * last;
                    stackOfDoubles.push(result);
                    break;
                case "/":
                    last = (double) stackOfDoubles.pop();
                    previous = stackOfDoubles.isEmpty() ? result : (double) stackOfDoubles.pop();
                    result = previous / last;
                    stackOfDoubles.push(result);
                    break;
                case "":
                case "(":
                    break;
                default:
                    stackOfDoubles.push(Double.parseDouble(symbol));
                    break;
            }
        }
        return String.valueOf(result);
    }
}
