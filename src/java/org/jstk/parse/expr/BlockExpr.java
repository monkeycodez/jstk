package org.jstk.parse.expr;

import org.jstk.jlang.Obj;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;

public final class BlockExpr implements  Expr{
	
	private final ExprStream stream;
	private final int lineno;
	
	public BlockExpr(ExprStream stream, int lineno){
		this.stream = stream;
		this.lineno = lineno;
	}

	@Override
	public Obj eval(ObjStack ostk, ExeEnv env){
		ObjStack stk = new ObjStack();
		stk.push(ostk.pop());
		stream.iterate(stk, env);
		return stk.pop();
	}

	@Override
	public int lineno(){
		return lineno;
	}

	
	
}
