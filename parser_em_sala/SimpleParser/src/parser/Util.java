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
        if (isThere(symbol)) {
            if (compatibilityTest(symbol, value)) {
                SymbolTable.getInstance().addSymbol(symbol, typeConvertion(getTokenType(getValue(symbol)), value));
            }
        } else {
            error("Variável " + symbol + " não declarada!");
        }
    }

    public static void declaration(Integer type, String symbol, Number value) {
        if (!isThere(symbol)) {
            if (typeMatchTest(type, value)) {
                SymbolTable.getInstance().addSymbol(symbol, typeConvertion(type, value));
            } else {
                Util.error("Tipo incorreto para atribuição!");
            }
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
            atrib(symbol, d);
        }
    }

    public static void error(String value) {
        //adicionar mensagem de erro mais detalhada
        System.err.println(value);
    }

    public static void print(Object value) {
        if (value != null) {
            if (value instanceof String) {
                System.out.println(value.toString().substring(1, value.toString().length() - 1));
            } else {
                System.out.println(value);
            }
        }
    }

    public static Number mathOperation(Integer op, Number x, Number y) {
        if (x != null && y != null) {
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
        return null;
    }

    public static Number typeConvertion(Double result, Number x, Number y) {
        if (x instanceof Integer && y instanceof Integer) {
            return result.intValue();
        }
        return result;
    }

    public static Number typeConvertion(Integer type, Number value) {
        switch (type) {
            case Prog_1Lexer.INT:
                return value.intValue();
            case Prog_1Lexer.DOUBLE:
                return value.doubleValue();
        }
        return null;
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

    public static Boolean typeMatchTest(Integer type, Number value) {
        switch (type) {
            case Prog_1Lexer.INT:
                if (value instanceof Integer) {
                    return true;
                }
                return false;
            case Prog_1Lexer.DOUBLE:
                if (value instanceof Double || value instanceof Integer) {
                    return true;
                }
                return false;
        }
        return false;
    }

    public static Boolean compatibilityTest(String symbol, Number value) {
        Number currentType = getTokenType(getValue(symbol));
        return typeMatchTest(currentType.intValue(), value);
    }

    public static Integer getTokenType(Number value) {
        if (value instanceof Integer) {
            return Prog_1Lexer.INT;
        } else if (value instanceof Double) {
            return Prog_1Lexer.DOUBLE;
        }
        return null;
    }

    public static Boolean logicOperation(Integer op, Boolean c1, Boolean c2) {
        if (c1 != null && c2 != null) {
            switch (op) {
                case 0:
                    return Boolean.logicalAnd(c1, c2);
                case 1:
                    return Boolean.logicalOr(c1, c2);
            }
        }
        return null;
    }
    
    public static Boolean relopOperation(String op, Number x, Number y){
        Double expr1 = x.doubleValue();
        Double expr2 = y.doubleValue();
        switch (op) {
            case ">":
                return (expr1 - expr2 > 0.0) ? Boolean.TRUE : Boolean.FALSE;
            case "<":
                return (expr1 - expr2 < 0.0) ? Boolean.TRUE : Boolean.FALSE;
            case ">=":
                return (expr1 - expr2 >= 0.0) ? Boolean.TRUE : Boolean.FALSE;
            case "<=":
                return (expr1 - expr2 <= 0.0) ? Boolean.TRUE : Boolean.FALSE;
            case "==":
                return (expr1 - expr2 == 0.0) ? Boolean.TRUE : Boolean.FALSE;
            case "!=":
                return (expr1 - expr2 != 0.0) ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }
}
