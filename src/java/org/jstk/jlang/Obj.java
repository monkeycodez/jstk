package org.jstk.jlang;

/**
 * Obj is the root of all classes used in JSTK.
 * 
 */
public class Obj{

	public String type(){
		return "Obj";
	}

	public BoolObj __eq__(Obj o){
		return BoolObj.toObj(this == o);
	}

	public BoolObj __ne__(Obj o){
		return BoolObj.toObj(this != o);
	}

	public BoolObj __gt__(Obj o){
		throw new JSTKRuntimeException("No > operator for " +
				this.type() + " and " + o.type());
	}

	public BoolObj __lt__(Obj o){
		throw new JSTKRuntimeException("No < operator for " +
				this.type() + " and " + o.type());
	}

	public BoolObj __ge__(Obj o){
		throw new JSTKRuntimeException("No >= operator for " +
				this.type() + " and " + o.type());
	}

	public BoolObj __le__(Obj o){
		throw new JSTKRuntimeException("No <= operator for " +
				this.type() + " and " + o.type());
	}

	public Obj __add__(Obj o){
		throw new JSTKRuntimeException("No + operator for " +
				this.type() + " and " + o.type());
	}

	public Obj __sub__(Obj o){
		throw new JSTKRuntimeException("No - operator for " +
				this.type() + " and " + o.type());
	}

	public Obj __mul__(Obj o){
		throw new JSTKRuntimeException("No * operator for " +
				this.type() + " and " + o.type());
	}

	public Obj __div__(Obj o){
		throw new JSTKRuntimeException("No / operator for " +
				this.type() + " and " + o.type());
	}

	public Obj __mod__(Obj o){
		throw new JSTKRuntimeException("No % operator for " +
				this.type() + " and " + o.type());
	}

	public Obj __inc__(){
		throw new JSTKRuntimeException("No ++ operator for " +
				this.type());
	}

	public Obj __dec__(){
		throw new JSTKRuntimeException("No -- operator for " +
				this.type());
	}

}
