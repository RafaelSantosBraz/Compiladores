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
    public Object visitDeclSimple(Prog_1Parser.DeclSimpleContext ctx) {
        Integer type = (Integer) visit(ctx.type());
        switch (type) {
            case Prog_1Lexer.INT:
                Util.declaration(type, ctx.VAR().getText(), 0);
                break;
            case Prog_1Lexer.DOUBLE:
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

    @Override
    public Object visitCondOR(Prog_1Parser.CondORContext ctx) {
        return Util.logicOperation(1, (Boolean) visit(ctx.cond()), (Boolean) visit(ctx.cdand()));
    }

    @Override
    public Object visitCdandAnd(Prog_1Parser.CdandAndContext ctx) {
        return Util.logicOperation(0, (Boolean) visit(ctx.cdand()), (Boolean) visit(ctx.cndts()));
    }

    @Override
    public Object visitCndtsExpr(Prog_1Parser.CndtsExprContext ctx) {
        return Util.getBoolean((Number) visit(ctx.expr()));
    }

    @Override
    public Object visitCndtsRelop(Prog_1Parser.CndtsRelopContext ctx) {
        return Util.relopOperation(ctx.RELOP().getText(), (Number) visit(ctx.e1), (Number) visit(ctx.e2));
    }

    @Override
    public Object visitCndtsNotExpr(Prog_1Parser.CndtsNotExprContext ctx) {
        return !Util.getBoolean((Number) visit(ctx.expr()));
    }

    @Override
    public Object visitCndtsNotRelop(Prog_1Parser.CndtsNotRelopContext ctx) {
        return !Util.relopOperation(ctx.RELOP().getText(), (Number) visit(ctx.e1), (Number) visit(ctx.e2));
    }

    @Override
    public Object visitCndtsCond(Prog_1Parser.CndtsCondContext ctx) {
        return visit(ctx.cond());
    }

}
