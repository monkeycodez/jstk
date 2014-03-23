package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;
import org.jstk.parse.expr.NameExpr;


public abstract class SMath extends Func{
		
	private SMath(){}

		
	public static final SMath add = new SMath(){
		@Override
		public String sname(){
			return "+";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1, n2;
			n2 = o.pop();
			n1 = o.pop();
			return n1.__add__(n2);
		}
				
	};
	
	public static final SMath sub = new SMath(){
		@Override
		public String sname(){
			return "-";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1, n2;
			n2 = o.pop();
			n1 = o.pop();
			return n1.__sub__(n2);
		}
				
	};
	
	public static final SMath mult = new SMath(){
		@Override
		public String sname(){
			return "*";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1, n2;
			n2 = o.pop();
			n1 = o.pop();
			return n1.__mul__(n2);
		}
				
	};
	
	public static final SMath div = new SMath(){
		@Override
		public String sname(){
			return "/";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1, n2;
			n2 = o.pop();
			n1 = o.pop();
			return n1.__div__(n2);
		}
				
	};
	
	public static final SMath mod = new SMath(){
		@Override
		public String sname(){
			return "%";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1, n2;
			n2 = o.pop();
			n1 = o.pop();
			return n1.__mod__(n2);
		}
				
	};
	
	public static final SMath inc = new SMath(){
		@Override
		public String sname(){
			return "++";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1 = o.pop();
			if(n1 instanceof CodeObj){
				Expr e = ((CodeObj)n1).getCode();
				if(e instanceof NameExpr){
					NameExpr n = (NameExpr) e;
					Obj val = env.get(n.getName()).__inc__();
					env.set_local(n.getName(), val);
					return val;	
				}
			}
			throw new JSTKRuntimeException(o + " is not a coderef");
		}
				
	};
	
	public static final SMath dec = new SMath(){
		@Override
		public String sname(){
			return "--";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj n1 = o.pop();
			if(n1 instanceof CodeObj){
				Expr e = ((CodeObj)n1).getCode();
				if(e instanceof NameExpr){
					NameExpr n = (NameExpr) e;
					Obj val = env.get(n.getName()).__dec__();
					env.set_local(n.getName(), val);
					return val;	
				}
			}
			throw new JSTKRuntimeException(o + " is not a coderef");
		}
				
	};
	
	public static final SMath eq = new SMath(){
		@Override
		public String sname(){
			return "==";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj o2 = o.pop();
			Obj o1 = o.pop();
			return o1.__eq__(o2);		
		}
				
	};
	
	public static final SMath ne = new SMath(){
		@Override
		public String sname(){
			return "!=";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj o2 = o.pop();
			Obj o1 = o.pop();
			return o1.__ne__(o2);		
		}
				
	};
	

	public static final SMath lt = new SMath(){
		@Override
		public String sname(){
			return "<";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj o2 = o.pop();
			Obj o1 = o.pop();
			return o1.__lt__(o2);
		}
				
	};
	
	public static final SMath gt = new SMath(){
		@Override
		public String sname(){
			return ">";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj o2 = o.pop();
			Obj o1 = o.pop();
			return o1.__gt__(o2);
		}
				
	};
	
	public static final SMath le = new SMath(){
		@Override
		public String sname(){
			return "<=";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj o2 = o.pop();
			Obj o1 = o.pop();
			return o1.__le__(o2);
		}
				
	};
	
	public static final SMath ge = new SMath(){
		@Override
		public String sname(){
			return ">=";
		}

		@Override
		public Obj exec(ObjStack o, ExeEnv env){
			Obj o2 = o.pop();
			Obj o1 = o.pop();
			return o1.__ge__(o2);
		}
				
	};
	
	


	
}
