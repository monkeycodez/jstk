/**
 * 
 */
package org.jstk.jlang;

import java.util.HashMap;
import java.util.Map;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ObjStack;
import org.jstk.parse.expr.NameExpr;

public class JSTKClass extends Func{

	private final Map<String, ? extends Obj> methods;
	private final String name;

	public JSTKClass(final String name,
		final Map<String, ? extends Obj> methods){
		this.name = name;
		this.methods = methods;
	}

	@Override
	public String sname(){
		return name;
	}

	/**
	 * Creates an instance of a class
	 * 
	 */
	@Override
	public Obj exec(final ObjStack ostk, final ExeEnv env){
		// TODO Create obj, set methods, call constructor
		final Obj newo = new Obj();
		newo.attribs.putAll(methods);
		final Obj cons = newo.__getattr__("__<init>__");
		ostk.push(newo);
		Func.exec_if_can(cons, ostk, env);
		return newo;
	}

	public static final Func defclass = new Func(){

		@Override
		public String sname(){
			return "defclass";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env){
			final Obj clz = ostk.pop();
			if(!(clz instanceof ListObj)){
				throw new JSTKRuntimeException(
					"defclass arg not a list");
			}
			final ListObj lst = (ListObj) clz;
			String name = "";
			try{
				name =
					((NameExpr) ((CodeObj) lst.get(0))
						.getCode()).getName();
			}catch(final Exception ex){
				throw new JSTKRuntimeException(
					"first val in list is not a coderef");
			}
			final Map<String, Func> methods = new HashMap<>();
			for(int i = 1; i < lst.size(); i++){
				try{
					final Func f = (Func) lst.get(i);
					methods.put(f.sname(), f);
					continue;
				}catch(final Exception ex){
					throw new JSTKRuntimeException(
						"Error: " + lst.get(i)
							+ " not a Func");
				}
			}
			final JSTKClass jclz = new JSTKClass(name, methods);
			env.set_local(name, jclz);
			return jclz;
		}

	};

}
