package org.jstk.parse;

import org.jstk.lex.*;
import org.jstk.parse.expr.BlockExpr;
import org.jstk.parse.expr.CodeExpr;
import org.jstk.parse.expr.LBraceExpr;
import org.jstk.parse.expr.ListExpr;
import org.jstk.parse.expr.NameExpr;
import org.jstk.parse.expr.RBraceExpr;
import org.jstk.parse.expr.RParenExpr;
import org.jstk.parse.expr.StringExpr;

public class Parser{
	private Lexer lex;

	public Parser(Lexer lex){
		this.lex = lex;
	}
	
	public Expr parseAll(){
		ExprStream e = new ExprStream();
		for(Expr x = parseNext(); x != null;
				x = parseNext()){
			e.add(x);
		}
		return new BlockExpr(e);
	}


	public Expr parseNext(){
		Token t = lex.next();
	//	System.out.println(t);
		switch(t.getType()){
			case IDEN:
				return NameExpr.create(t);
			case COMMA:
				return new CodeExpr(parseNext());
			case STRING:
				return new StringExpr(t.getText());
			case LBRACE:
				ExprStream str = new ExprStream();
				str.add(new LBraceExpr());
				Expr next = parseNext();
				for(; !(next instanceof RBraceExpr) &&
						next != null;
						next = parseNext()){
					str.add(next);
				}
				str.add(next);
				return new BlockExpr(str);
			case RBRACE:
				return new RBraceExpr();
				
			case LPAREN:
				str = new ExprStream();
				next = parseNext();
				for(; !(next instanceof RParenExpr) &&
						next != null;
						next = parseNext()){
					str.add(next);
				}
				return new ListExpr(str);
			case RPAREN:
				return new RParenExpr();
			case EOF:
				return null;
			default:
				break;
		}
		return null;
	}


}
