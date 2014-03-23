package org.jstk.jlang;


public class TagObj extends Obj{
	
	private final String tag;

	public TagObj(String tag){
		this.tag = tag;
	}
	
	
	
	@Override
	public BoolObj __eq__(Obj o){
		if(o instanceof TagObj){
			return BoolObj.toObj(tag.equals(((TagObj)o).tag));
		}
		return super.__eq__(o);
	}



	@Override
	public BoolObj __ne__(Obj o){
		if(o instanceof TagObj){
			return BoolObj.toObj(!tag.equals(((TagObj)o).tag));
		}
		return super.__ne__(o);
	}

	@Override
	public String toString(){
		return tag;
	}
}
