package org.jstk.parse;

import java.util.*;
import org.jstk.lex.*;

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

}
