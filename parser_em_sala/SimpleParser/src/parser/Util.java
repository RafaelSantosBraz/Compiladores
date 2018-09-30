/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.Scanner;

/**
 * FACADE?
 *
 * @author wellington
 */
public class Util {

    public static Boolean getBoolean(Number d) {
        return !(d == null || d.doubleValue() <= 0);
    }

    public static Number getValue(String symbol) {
        Number v = SymbolTable.getInstance().getSymbol(symbol);
        if (v == null) {
            error(String.format("variável %s não foi declarada", symbol));
        }
        return v;
    }

    public static Boolean isThere(String Symbol) {
        return SymbolTable.getInstance().isThere(Symbol);
    }

    public static void atrib(String symbol, Number value) {
        SymbolTable.getInstance().addSymbol(symbol, value);
    }

    public static void declaration(String symbol, Number value) {
        if (!isThere(symbol)) {
            atrib(symbol, value);
        } else {
            Util.error("Variável " + symbol + " já declarada!");
        }
    }

    public static Number read() {
        String n = new Scanner(System.in).nextLine();
        return stringNumberConvertion(n);
    }

    public static void readSymbol(String symbol) {
        Number d = read();
        if (d == null) {
            error("entrada invalida");
        } else {
            SymbolTable.getInstance().addSymbol(symbol, d);
        }
    }

    public static void error(String value) {
        //adicionar mensagem de erro mais detalhada
        System.err.println(value);
    }

    public static void print(Object value) {
        if (value instanceof String) {
            System.out.println(value.toString().substring(1, value.toString().length() - 1));
        } else {
            System.out.println(value);
        }
    }

    public static Number mathOperation(Integer op, Number x, Number y) {
        Double n1 = x.doubleValue();
        Double n2 = y.doubleValue();
        Double result = 0.0;
        switch (op) {
            case 0:
                result = n1 + n2;
                break;
            case 1:
                result = n1 - n2;
                break;
            case 2:
                result = n1 * n2;
                break;
            case 3:
                result = n1 / n2;
                break;
        }
        return typeConvertion(result, x, y);
    }

    public static Number typeConvertion(Double result, Number x, Number y) {
        if (x instanceof Integer && y instanceof Integer) {
            return result.intValue();
        }
        return result;
    }

    public static Number stringNumberConvertion(String n) {
        try {
            return Integer.parseInt(n);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(n);
            } catch (NumberFormatException f) {
                return null;
            }
        }
    }

}
