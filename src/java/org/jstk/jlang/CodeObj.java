package org.jstk.jlang;

import org.jstk.parse.Expr;


public class CodeObj extends Obj{
	
	private Expr code;

	public CodeObj(Expr code) {
		super();
		this.code = code;
	}

	
	public Expr getCode(){
		return code;
	}
	
	

}
