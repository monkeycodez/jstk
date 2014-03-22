package org.jstk.jlang;

public class LObj extends Obj{

	public final long num;

	public LObj(long num){
		this.num = num;
	}
	
	@Override
	public BoolObj __eq__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num == ((LObj)o).num);
		}
		return BoolObj.FALSE;
	}

	@Override
	public BoolObj __gt__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num > ((LObj)o).num);
		}
		return BoolObj.FALSE;
	}

	@Override
	public BoolObj __lt__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num < ((LObj)o).num);
		}
		return BoolObj.FALSE;
	}

	@Override
	public BoolObj __ge__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num >= ((LObj)o).num);
		}
		return BoolObj.FALSE;
	}

	@Override
	public BoolObj __le__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num <= ((LObj)o).num);
		}
		return BoolObj.FALSE;
	}

	public String toString(){
		return num+"";
	}
}
