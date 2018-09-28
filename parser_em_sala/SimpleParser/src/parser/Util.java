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

    public static Boolean getBoolean(Double d) {
        return !(d == null || d <= 0);
    }

    public static Double getValue(String symbol) {
        Double v = (Double) SymbolTable.getInstance().getSymbol(symbol);
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
            Util.error("Variável já declarada!");
        }
    }

    public static Number read() {
        try {
            return Integer.parseInt(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(new Scanner(System.in).nextLine());
            } catch (NumberFormatException f) {
                return null;
            }
        }
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

    public static void print(String value) {
        System.out.println(value.substring(1, value.length() - 1));
    }

    public static void print(Double value) {
        System.out.println(value);
    }
}
