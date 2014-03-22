package org.jstk.parse;

import java.util.*;
import org.jstk.lex.*;
import org.jstk.parse.expr.LBraceExpr;
import org.jstk.parse.expr.RBraceExpr;

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
				for(; !(next instanceof RBraceExpr);
						next = parseNext()){
					str.add(next);
				}
				str.add(next);
				return new BlockExpr(str);
			case RBRACE:
				return new RBraceExpr();
				
			case LPAREN:
				//Opening expr
				break;
			case RPAREN:
				return null;
			case EOF:
				return null;
			default:
				break;
		}
		return null;
	}


}
