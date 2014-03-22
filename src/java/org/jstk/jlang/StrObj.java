package org.jstk.jlang;


public class StrObj extends Obj{
	
	private String str;

	public StrObj(String str) {
		super();
		this.str = str;
	}
	
	
	
	@Override
	public BoolObj __eq__(Obj o){
		if(o instanceof StrObj){
			return BoolObj.toObj(str.equals(((StrObj)o).str));
		}
		return super.__eq__(o);
	}



	public String getStr(){
		return str;
	}
	
	public String toString(){
		return str;
	}

}
