package org.jstk.parse;

import java.util.*;
import org.jstk.lex.*;

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
