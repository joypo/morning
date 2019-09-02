package com.example.morning.demo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sunx
 * @date 2019-08-27
 * @description
 */
public class Calculator {
    public static final String DIVIZION_BY_ZERO = "Divizion by zero";

    private static final Set<Character> PLUS_MINUS = new HashSet<Character>() {{
        add('+');
        add('-');
    }};

    private static final Set<Character> MULTIPLY_DIVIDE = new HashSet<Character>() {{
        add('*');
        add('/');
    }};

    private static final Set<Character> OPERATORS = new HashSet<Character>() {{
        addAll(PLUS_MINUS);
        addAll(MULTIPLY_DIVIDE);
    }};

    public static final Pattern EXPRESSION_PART_PATTERN = Pattern.compile("((\\d*\\.\\d+)|(\\d+)|([\\+\\-\\*/\\(\\)]))");

    public static String calculateExpression(String s) {

        //System.out.println("------------------------ " + s);
        s = s.replaceAll(" ", "");

        List<String> parsed = parseExpression(s);
        //System.out.println("parsed " + parsed);

        List<String> exp = transformToPostfixNotation(parsed);
        //System.out.println(s + " = " + exp);

        Stack<Double> result = new Stack<>();

        String error = null;

        loop:
        for (String e : exp) {

            if (isOperator(e)) {
                switch (e) {
                    case "/": {
                        double p2 = result.pop();
                        double p1 = result.pop();

                        if (p2 == 0) {
                            error = DIVIZION_BY_ZERO;
                            break loop;
                        }

                        result.push(p1 / p2);
                    }
                    break;

                    case "*": {
                        double p1 = result.pop();
                        double p2 = result.pop();

                        result.push(p1 * p2);
                    }
                    break;

                    case "+": {
                        if (result.size() != 1) {
                            double p1 = result.pop();
                            double p2 = result.pop();

                            result.push(p1 + p2);
                        }
                    }
                    break;

                    case "-": {
                        if (result.size() != 1) {
                            double p1 = result.pop();
                            double p2 = result.pop();

                            result.push(p2 - p1);
                        } else {
                            double p1 = result.pop();
                            result.push(-p1);
                        }
                    }
                    break;
                }
            } else {
                result.push(Double.parseDouble(e));
            }
        }

        //System.out.println("result stack " + result);
        if (error != null) {
            //System.out.println(error);
            return error;
        } else {
            return result.pop().toString();
        }
    }

    private static List<String> transformToPostfixNotation(List<String> parsed) {
        List<String> exp = new ArrayList<>();
        Stack<String> operStack = new Stack<>();
        for (String currElem : parsed) {

            if (isOperator(currElem)) {

                while (!operStack.isEmpty() &&
                        !operStack.peek().equals("(") &&
                        (compareOperationPriority(operStack.peek(), currElem) >= 0)) {

                    exp.add(operStack.pop());
                }
                operStack.push(currElem);

            } else if (currElem.equals("(")) {
                operStack.push(currElem);
            } else if (")".equals(currElem)) {
                while (!"(".equals(operStack.peek())) {
                    exp.add(operStack.pop());
                }
                operStack.pop();
            } else {
                exp.add(currElem);
            }
            //System.out.println(/*stack + " " +*/ exp);
        }

        while (!operStack.isEmpty()) {
            exp.add(operStack.pop());
        }
        return exp;
    }

    private static List<String> parseExpression(String s) {

        Matcher m = EXPRESSION_PART_PATTERN.matcher(s);

        List<String> parsed = new ArrayList<>();

        while (m.find()) {

            String elem = m.group();

            if (!isOperator(elem)) {

                if ((parsed.size() == 1) && isOperator(parsed.get(0)) && !"(".equals(elem)) {
                    char prev = parsed.get(0).charAt(0);
                    if (PLUS_MINUS.contains(Character.valueOf(prev))) {
                        parsed.remove(0);
                        elem = prev + elem;
                    }
                } else if ((parsed.size() >= 2) && !elem.equals("(")) {
                    String prev = parsed.get(parsed.size() - 1);
                    String prevPrev = parsed.get(parsed.size() - 2);
                    if (isOperator(prev) && isOperator(prevPrev)) {
                        parsed.remove(parsed.size() - 1);
                        elem = prev + elem;
                    }
                }
            }

            parsed.add(elem);
        }
        return parsed;
    }

    private static int compareOperationPriority(String oper1, String oper2) {
        Integer w1 = MULTIPLY_DIVIDE.contains(oper1.charAt(0)) ? 1 : 0;
        Integer w2 = MULTIPLY_DIVIDE.contains(oper2.charAt(0)) ? 1 : 0;

        return w1.compareTo(w2);
    }

    private static boolean isOperator(String o) {
        if (o.length() != 1) {
            return false;
        }
        char t = o.charAt(0);
        return OPERATORS.contains(t);
    }
}
