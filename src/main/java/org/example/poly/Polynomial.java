package org.example.poly;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Polynomial {
    private String s;

    public Polynomial(String s) {
        this.s = s;
    }


    int calc() {
        Stack<String> stk = new Stack<>();
        StringTokenizer st = new StringTokenizer(s, "+-*/%() ", true);
        List<String> r = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String cur = st.nextToken();
//            System.out.println(cur);
            if (cur.equals(" ")) continue;
            switch (cur) {
                case ")" -> {
                    while (!stk.isEmpty() && !stk.peek().equals("(")) {
                        r.add(stk.pop());
                    }
                    stk.pop();
                }
                case "(" -> {
                    stk.push(cur);
                }
                case "*", "/", "%" -> {
                    while (!stk.isEmpty() && (stk.peek().equals("*") || stk.peek().equals("/") || stk.peek().equals("%"))) {
                        r.add(stk.pop());
                    }
                    stk.push(cur);
                }
                case "+", "-" -> {
                    while (!stk.isEmpty() && !stk.peek().equals("(")) {
                        r.add(stk.pop());
                    }
                    stk.push(cur);
                }
                default -> {
                    r.add(cur);
                }
            }
        }

        while (!stk.isEmpty()) {
            r.add(stk.pop());
        }

        while (r.size() >= 3) {
            for (int i = 2; i < r.size(); i++) {
                switch (r.get(i)) {
                    case "+" ->
                            r.set(i - 2, String.valueOf(Integer.parseInt(r.get(i - 2)) + Integer.parseInt(r.get(i - 1))));
                    case "-" ->
                            r.set(i - 2, String.valueOf(Integer.parseInt(r.get(i - 2)) - Integer.parseInt(r.get(i - 1))));
                    case "*" ->
                            r.set(i - 2, String.valueOf(Integer.parseInt(r.get(i - 2)) * Integer.parseInt(r.get(i - 1))));
                    case "/" ->
                            r.set(i - 2, String.valueOf(Integer.parseInt(r.get(i - 2)) / Integer.parseInt(r.get(i - 1))));
                    case "%" ->
                            r.set(i - 2, String.valueOf(Integer.parseInt(r.get(i - 2)) % Integer.parseInt(r.get(i - 1))));
                    default -> {
                        continue;
                    }
                }
                r.remove(i - 1);
                r.remove(i - 1);
                break;
            }
        }
        return Integer.parseInt(r.get(0));
    }
}

