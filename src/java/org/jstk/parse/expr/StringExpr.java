package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.jlang.StrObj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;



public class StringExpr implements Expr{
	
	private StrObj str;
	
	public StringExpr(String s){
		str = new StrObj(s);
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		return this.str;
	}

}
