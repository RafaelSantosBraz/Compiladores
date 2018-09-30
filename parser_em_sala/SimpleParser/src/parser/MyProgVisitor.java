/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

/**
 *
 * @author wellington
 */
public class MyProgVisitor extends Prog_1BaseVisitor<Object> {

    @Override
    public Object visitCmdExpr(Prog_1Parser.CmdExprContext ctx) {
        Util.print(visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitAtrib(Prog_1Parser.AtribContext ctx) {
        Util.atrib(ctx.VAR().getText(), (Number) visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitWriteExpr(Prog_1Parser.WriteExprContext ctx) {
        Util.print(visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitWriteStr(Prog_1Parser.WriteStrContext ctx) {
        Util.print(ctx.STR().getText());
        return null;
    }

    @Override
    public Object visitRead(Prog_1Parser.ReadContext ctx) {
        Util.readSymbol(ctx.VAR().getText());
        return null;
    }

    @Override
    public Object visitExprPlus(Prog_1Parser.ExprPlusContext ctx) {
        return Util.mathOperation(0, (Number) visit(ctx.expr()), (Number) visit(ctx.term()));
    }

    @Override
    public Object visitExprMin(Prog_1Parser.ExprMinContext ctx) {
        return Util.mathOperation(1, (Number) visit(ctx.expr()), (Number) visit(ctx.term()));
    }

    @Override
    public Object visitExprHided(Prog_1Parser.ExprHidedContext ctx) {
        return Util.mathOperation(0, (Number) visit(ctx.expr()), (Number) visit(ctx.term()));
    }

    @Override
    public Object visitTermMult(Prog_1Parser.TermMultContext ctx) {
        return Util.mathOperation(2, (Number) visit(ctx.term()), (Number) visit(ctx.fact()));
    }

    @Override
    public Object visitTermDiv(Prog_1Parser.TermDivContext ctx) {
        return Util.mathOperation(3, (Number) visit(ctx.term()), (Number) visit(ctx.fact()));
    }

    @Override
    public Object visitFactNum(Prog_1Parser.FactNumContext ctx) {
        return Util.stringNumberConvertion(ctx.NUM().getText());
    }

    @Override
    public Object visitFactVar(Prog_1Parser.FactVarContext ctx) {
        return Util.getValue(ctx.VAR().getText());
    }

    @Override
    public Object visitFactExpr(Prog_1Parser.FactExprContext ctx) {
        return (Number) visit(ctx.expr());
    }

    @Override
    public Object visitIfStm(Prog_1Parser.IfStmContext ctx) {
        Boolean cond = (Boolean) visit(ctx.cond());
        if (cond) {
            return visit(ctx.block());
        }
        return null;
    }

    @Override
    public Object visitIfStmElse(Prog_1Parser.IfStmElseContext ctx) {
        Boolean cond = (Boolean) visit(ctx.cond());
        if (cond) {
            return visit(ctx.b1);
        } else {
            return visit(ctx.b2);
        }
    }

    @Override
    public Object visitCondExpr(Prog_1Parser.CondExprContext ctx) {
        return Util.getBoolean((Number) visit(ctx.expr()));
    }

    @Override
    public Object visitCondRelop(Prog_1Parser.CondRelopContext ctx) {        
        Double expr1 = ((Number) visit(ctx.e1)).doubleValue();
        Double expr2 = ((Number) visit(ctx.e2)).doubleValue();
        switch (ctx.RELOP().getText()) {
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

    @Override
    public Object visitDeclSimple(Prog_1Parser.DeclSimpleContext ctx) {
        Integer type = (Integer) visit(ctx.type());
        switch (type) {
            case 11:
                Util.declaration(type, ctx.VAR().getText(), 0);
                break;
            case 12:
                Util.declaration(type, ctx.VAR().getText(), 0.0);
                break;
        }
        return null;
    }

    @Override
    public Object visitDeclValue(Prog_1Parser.DeclValueContext ctx) {
        Util.declaration((Integer) visit(ctx.type()), ctx.VAR().getText(), (Number) visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitTypeInt(Prog_1Parser.TypeIntContext ctx) {
        return ctx.INT().getSymbol().getType();
    }

    @Override
    public Object visitTypeDouble(Prog_1Parser.TypeDoubleContext ctx) {
        return ctx.DOUBLE().getSymbol().getType();
    }

}
