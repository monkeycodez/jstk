package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.jlang.TagObj;
import org.jstk.lex.Token;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;


public class TagExpr implements Expr{
	
	private final int lineno;
	private final TagObj tag;
	
	public TagExpr(Token t){
		tag = new TagObj(t.getText());
		lineno = t.getLineno();
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		return tag;
	}

	@Override
	public int lineno(){
		return lineno;
	}

}
