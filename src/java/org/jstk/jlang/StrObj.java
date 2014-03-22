package org.jstk.jlang;


public class StrObj extends Obj{
	
	private String str;

	public StrObj(String str) {
		super();
		this.str = str;
	}

	
	public String getStr(){
		return str;
	}
	
	public String toString(){
		return str;
	}

}
