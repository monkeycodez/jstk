package org.jstk.jlang;

public class LObj extends Obj{

	private final long num;

	public LObj(long num){
		this.num = num;
	}
	

	@Override
	public String toString(){
		return num+"";
	}
	
	@Override
	public String type(){
		return "LObj";
	}
	
	@Override
	public Obj __eq__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num == ((LObj)o).num);
		}
		return super.__eq__(o);
	}

	@Override
	public Obj __gt__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num > ((LObj)o).num);
		}
		return super.__gt__(o);
	}

	@Override
	public Obj __lt__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num < ((LObj)o).num);
		}
		return super.__lt__(o);
	}

	@Override
	public Obj __ge__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num >= ((LObj)o).num);
		}
		return super.__ge__(o);
	}

	@Override
	public Obj __le__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num <= ((LObj)o).num);
		}
		return super.__le__(o);
	}


	@Override
	public Obj __ne__(Obj o){
		if(o instanceof LObj){
			return BoolObj.toObj(num != ((LObj)o).num);
		}
		return super.__ne__(o);
	}


	@Override
	public Obj __add__(Obj o){
		if(o instanceof LObj){
			return new LObj(num + ((LObj)o).num);
		}
		return super.__add__(o);
	}


	@Override
	public Obj __sub__(Obj o){
		if(o instanceof LObj){
			return new LObj(num - ((LObj)o).num);
		}
		return super.__sub__(o);
	}


	@Override
	public Obj __mul__(Obj o){
		if(o instanceof LObj){
			return new LObj(num * ((LObj)o).num);
		}
		return super.__mul__(o);
	}


	@Override
	public Obj __div__(Obj o){
		if(o instanceof LObj){
			return new LObj(num / ((LObj)o).num);
		}
		return super.__div__(o);
	}


	@Override
	public Obj __mod__(Obj o){
		if(o instanceof LObj){
			return new LObj(num % ((LObj)o).num);
		}
		return super.__mod__(o);
	}


	@Override
	public Obj __inc__(){
		return new LObj(num + 1);
	}


	@Override
	public Obj __dec__(){
		return new LObj(num - 1);
	}
	
	
	
}
