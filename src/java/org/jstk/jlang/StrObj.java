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

	@Override
	public String type(){
		return "StrObj";
	}

	@Override
	public BoolObj __ne__(Obj o){
		if(o instanceof StrObj){
			return BoolObj.toObj(!str.equals(((StrObj)o).str));
		}
		return super.__ne__(o);
	}

	@Override
	public Obj __add__(Obj o){
		if(o instanceof StrObj){
			return new StrObj(str + ((StrObj)o).str);
		}
		return super.__add__(o);
	}

	public String getStr(){
		return str;
	}
	
	public String toString(){
		return str;
	}

}
