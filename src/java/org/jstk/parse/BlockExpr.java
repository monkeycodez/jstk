package org.jstk.parse;

import org.jstk.jlang.Obj;

public class BlockExpr implements  Expr{
	
	private ExprStream stream;
	
	public BlockExpr(ExprStream stream){
		this.stream = stream;
	}

	@Override
	public Obj eval(ObjStack stk_, ExeEnv env, ExprStream str){
		ObjStack stk = new ObjStack();
		for(Expr e: stream){
			stk.push(e.eval(stk, env, stream));
		//	System.out.println(e);
		}
		return stk.pop();
	}

}
