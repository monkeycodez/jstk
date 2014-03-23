package org.jstk.parse.expr;

import org.jstk.jlang.ListObj;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


public class ListExpr implements Expr{
	
	private final ExprStream list;
	private final int lineno;
	
	public ListExpr(ExprStream e, int lineno){
		list = e;
		this.lineno = lineno;
	}

	@Override
	public Obj eval(ObjStack stk_, ExeEnv env){
		ObjStack stk = new ObjStack();
		list.iterate(stk, env);
		return new ListObj(stk.to_list());
	}

	@Override
	public int lineno(){
		return lineno;
	}
	

}
