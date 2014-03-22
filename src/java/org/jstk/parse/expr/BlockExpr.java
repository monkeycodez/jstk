package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;

public class BlockExpr implements  Expr{
	
	private ExprStream stream;
	
	public BlockExpr(ExprStream stream){
		this.stream = stream;
	}

	@Override
	public Obj eval(ObjStack ostk, ExeEnv env){
		ObjStack stk = new ObjStack();
		stk.push(ostk.pop());
		stream.reset();
		for(Expr e: stream){
			stk.push(e.eval(stk, env));
		//	System.out.println(e);
		}
		return stk.pop();
	}

}
