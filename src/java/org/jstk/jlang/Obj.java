package org.jstk.jlang;

public class Obj{
	
	public BoolObj __eq__(Obj o){
		return BoolObj.toObj(this == o);
	}
	

	public BoolObj __gt__(Obj o){
		return BoolObj.FALSE;
	}
	

	public BoolObj __lt__(Obj o){
		return BoolObj.FALSE;
	}
	

	public BoolObj __ge__(Obj o){
		return BoolObj.FALSE;
	}
	

	public BoolObj __le__(Obj o){
		return BoolObj.FALSE;
	}
	
}
