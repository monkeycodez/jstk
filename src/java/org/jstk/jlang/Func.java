package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


public abstract class Func extends Obj{

	public abstract String sname();
	
	public abstract Obj exec(ObjStack o, ExprStream e, ExeEnv env);
	
	public static final Func print = new Func(){

		@Override
		public String sname(){
			return "print";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			System.out.print(o.pop());
			return NullObj.nul;
		}
		
	};
	
	public static final Func println = new Func(){

		@Override
		public String sname(){
			return "println";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			System.out.println(o.pop());
			return NullObj.nul;
		}
		
	};
}
