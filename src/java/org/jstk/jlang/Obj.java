package org.jstk.jlang;

/**
 * Obj is the root of all classes used in JSTK.
 * 
 */
public class Obj{

	public String type(){
		return "Obj";
	}

	public Obj __getattr__(String attr){
		throw new JSTKRuntimeException("No such attribute: " + attr +
			" on " + type());
	}
	
	public Obj __setattr__(String attr, Obj val){
		throw new JSTKRuntimeException("Cannot set attibute: " + attr +
			" on " + type());
	}

	public Obj __eq__(Obj o){
		return BoolObj.toObj(this == o);
	}

	public Obj __ne__(Obj o){
		return BoolObj.toObj(this != o);
	}

	public Obj __gt__(Obj o){
		throw new JSTKRuntimeException("No > operator for " +
			this.type() + " and " + o.type());
	}

	public Obj __lt__(Obj o){
		throw new JSTKRuntimeException("No < operator for " +
			this.type() + " and " + o.type());
	}

	public Obj __ge__(Obj o){
		throw new JSTKRuntimeException("No >= operator for " +
			this.type() + " and " + o.type());
	}

	public Obj __le__(Obj o){
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
