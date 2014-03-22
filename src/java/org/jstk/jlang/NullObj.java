package org.jstk.jlang;


public class NullObj extends Obj{
	
	private NullObj(){}
	
	public static final NullObj nul = new NullObj();

	public String toString(){
		return "null";
	}
}
