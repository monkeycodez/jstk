package org.jstk.parse;

import java.util.*;

public class ExprStream implements Iterator<Expr>, Iterable<Expr>{

	private ArrayList<Expr> exprs = new ArrayList<>();
	private int curr = 0;

	public ExprStream(){}

	@Override
	public Expr next(){
		return exprs.get(curr++);
	}

	public Expr getrel(int i){
		return exprs.get(curr + i);
	}

	public void add(Expr e){
		exprs.add(e);
	}
	
	public void reset(){
		curr = 0;
	}

	@Override
	public Iterator<Expr> iterator(){
		return this;
	}

	@Override
	public boolean hasNext(){
		return curr < exprs.size();
	}
	
	@Override
	public void remove(){
		throw new UnsupportedOperationException();
	}
	
	//Iterates over the stream, using stk as the Obj Stack, and env as
	//the var stack. modifies stk
	public void iterate(ObjStack stk, ExeEnv env){
		this.reset();
		for(Expr e: this){
			env.set_cline(e.lineno());
			stk.push(e.eval(stk, env));
		//	System.out.println(e);
		}
	}

}
