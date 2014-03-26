package org.jstk.jlang;

import java.util.HashMap;
import java.util.Map;

/**
 * Obj is the root of all classes used in JSTK.
 * 
 */
public class Obj{

	protected Map<String, Obj> attribs = new HashMap<>();

	public String type(){
		return "Obj";
	}

	public Obj __getattr__(final String attr){
		final Obj attrib = attribs.get(attr);
		if(attrib != null){
			return attrib;
		}
		throw new JSTKRuntimeException("No such attribute: " + attr
			+ " on " + type());
	}

	public Obj __setattr__(final String attr, final Obj val){
		attribs.put(attr, val);
		return val;
	}

	public Obj __eq__(final Obj o){
		return BoolObj.toObj(this == o);
	}

	public Obj __ne__(final Obj o){
		return BoolObj.toObj(this != o);
	}

	public Obj __gt__(final Obj o){
		throw new JSTKRuntimeException("No > operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __lt__(final Obj o){
		throw new JSTKRuntimeException("No < operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __ge__(final Obj o){
		throw new JSTKRuntimeException("No >= operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __le__(final Obj o){
		throw new JSTKRuntimeException("No <= operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __add__(final Obj o){
		throw new JSTKRuntimeException("No + operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __sub__(final Obj o){
		throw new JSTKRuntimeException("No - operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __mul__(final Obj o){
		throw new JSTKRuntimeException("No * operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __div__(final Obj o){
		throw new JSTKRuntimeException("No / operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __mod__(final Obj o){
		throw new JSTKRuntimeException("No % operator for "
			+ this.type() + " and " + o.type());
	}

	public Obj __inc__(){
		throw new JSTKRuntimeException("No ++ operator for "
			+ this.type());
	}

	public Obj __dec__(){
		throw new JSTKRuntimeException("No -- operator for "
			+ this.type());
	}

}
