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
        Util.print((Double) visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitAtrib(Prog_1Parser.AtribContext ctx) {
        Util.atrib(ctx.VAR().getText(), (Double) visit(ctx.expr()));
        return null;
    }

    @Override
    public Object visitWriteExpr(Prog_1Parser.WriteExprContext ctx) {
        Util.print((Double) visit(ctx.expr()));
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
        return (Double) visit(ctx.expr()) + (Double) visit(ctx.term());
    }

    @Override
    public Object visitExprMin(Prog_1Parser.ExprMinContext ctx) {
        return (Double) visit(ctx.expr()) - (Double) visit(ctx.term());
    }

    @Override
    public Object visitExprHided(Prog_1Parser.ExprHidedContext ctx) {
        return (Double) visit(ctx.expr()) + (Double) visit(ctx.term());
    }

    @Override
    public Object visitTermMult(Prog_1Parser.TermMultContext ctx) {
        return (Double) visit(ctx.term()) * (Double) visit(ctx.fact());
    }

    @Override
    public Object visitTermDiv(Prog_1Parser.TermDivContext ctx) {
        return (Double) visit(ctx.term()) / (Double) visit(ctx.fact());
    }

    @Override
    public Object visitFactNum(Prog_1Parser.FactNumContext ctx) {
        return Double.parseDouble(ctx.NUM().getText());
    }

    @Override
    public Object visitFactVar(Prog_1Parser.FactVarContext ctx) {
        return Util.getValue(ctx.VAR().getText());
    }

    @Override
    public Object visitFactExpr(Prog_1Parser.FactExprContext ctx) {
        return (Double) visit(ctx.expr());
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
        Double value = (Double) visit(ctx.expr());
        return Util.getBoolean(value);
    }

    @Override
    public Object visitConfRelop(Prog_1Parser.ConfRelopContext ctx) {
        Double var = Util.getValue(ctx.VAR().getText());
        Double expr = (Double) visit(ctx.expr());
        switch (ctx.RELOP().getText()) {
            case ">":
                return (var - expr > 0) ? Boolean.TRUE : Boolean.FALSE;
            case "<":
                return (var - expr < 0) ? Boolean.TRUE : Boolean.FALSE;
            case ">=":
                return (var - expr >= 0) ? Boolean.TRUE : Boolean.FALSE;
            case "<=":
                return (var - expr <= 0) ? Boolean.TRUE : Boolean.FALSE;
            case "==":
                return (var - expr == 0) ? Boolean.TRUE : Boolean.FALSE;
            case "!=":
                return (var - expr != 0) ? Boolean.TRUE : Boolean.FALSE;
        }
        return null;
    }

}