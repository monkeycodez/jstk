package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;


public class SMath extends Func{
	
	private String type;
	
	public SMath(String type){
		this.type = type;
	}
	
	private SMath(){}

	@Override
	public String sname(){
		return type;
	}

	@Override
	public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
		Obj n1, n2;
		LObj l1 = null, l2 = null;
		n1 = o.pop();
		n2 = o.pop();
		if(n1 instanceof LObj){
			l1 = (LObj) n1;
		}
		if(n2 instanceof LObj){
			l2 = (LObj) n2;
		}
		switch(type){
			case "*":
				return new LObj(l1.num * l2.num);
			case "+":
				return new LObj(l1.num + l2.num);
			case "-":
				return new LObj(l1.num - l2.num);
			case "/":
				return new LObj(l1.num / l2.num);
			
			default:
				return null;
		}
	}
	
	public static final SMath add = new SMath(){
		@Override
		public String sname(){
			return "+";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			Obj n1, n2;
			LObj l1 = null, l2 = null;
			n1 = o.pop();
			n2 = o.pop();
			if(n1 instanceof LObj){
				l1 = (LObj) n1;
			}
			if(n2 instanceof LObj){
				l2 = (LObj) n2;
			}
			return new LObj(l1.num + l2.num);
		}
				
	};
	
	public static final SMath sub = new SMath(){
		@Override
		public String sname(){
			return "-";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			Obj n1, n2;
			LObj l1 = null, l2 = null;
			n1 = o.pop();
			n2 = o.pop();
			if(n1 instanceof LObj){
				l1 = (LObj) n1;
			}
			if(n2 instanceof LObj){
				l2 = (LObj) n2;
			}
			return new LObj(l1.num - l2.num);
		}
				
	};
	
	public static final SMath mult = new SMath(){
		@Override
		public String sname(){
			return "*";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			Obj n1, n2;
			LObj l1 = null, l2 = null;
			n1 = o.pop();
			n2 = o.pop();
			if(n1 instanceof LObj){
				l1 = (LObj) n1;
			}
			if(n2 instanceof LObj){
				l2 = (LObj) n2;
			}
			return new LObj(l1.num * l2.num);
		}
				
	};
	
	public static final SMath div = new SMath(){
		@Override
		public String sname(){
			return "/";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			Obj n1, n2;
			LObj l1 = null, l2 = null;
			n1 = o.pop();
			n2 = o.pop();
			if(n1 instanceof LObj){
				l1 = (LObj) n1;
			}
			if(n2 instanceof LObj){
				l2 = (LObj) n2;
			}
			return new LObj(l1.num / l2.num);
		}
				
	};

	
}
