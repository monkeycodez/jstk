package org.jstk.jlang;


public final class NullObj extends Obj{
	
	private NullObj(){}
	
	public static final NullObj nul = new NullObj();

	public String toString(){
		return "null";
	}

	@Override
	public String type(){
		return "NullObj";
	}
	

}
