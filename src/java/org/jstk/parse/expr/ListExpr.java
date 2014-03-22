package org.jstk.parse.expr;

import org.jstk.jlang.ListObj;
import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


public class ListExpr implements Expr{
	
	private ExprStream list;
	
	public ListExpr(ExprStream e){
		list = e;
	}

	@Override
	public Obj eval(ObjStack stk_, ExeEnv env){
		ObjStack stk = new ObjStack();
		for(Expr e: list){
			stk.push(e.eval(stk, env));
		}
		return new ListObj(stk.to_list());
	}

}
