package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;
import org.jstk.parse.expr.NameExpr;


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
	
	public static final Func eval = new Func(){

		@Override
		public String sname(){
			return "eval";
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			Obj prev = o.pop();
			if(prev instanceof CodeObj){
				CodeObj c = (CodeObj)prev;
				return c.getCode().eval(o, env, e);
			}
			return prev;
		}
		
	};
	
	public static final Func set = new Func(){

		@Override
		public String sname(){
			return "set";
		}

		@Override
		public Obj exec(ObjStack stk, ExprStream e, ExeEnv env){
			Obj p = stk.pop();
			Obj val = stk.pop();
			if(p instanceof CodeObj){
				CodeObj c = (CodeObj) p;
				if(c.getCode() instanceof NameExpr){
					NameExpr n = (NameExpr) c.getCode();
					env.set_local(n.getName(), val);
					return val;		
				}
			}
			System.err.println("bad assign");
			return null;
		}
		
	};
	
	public static final Func cond = new Func(){

		@Override
		public String sname(){
			return "cond";
		}
		
		private boolean istrue(Obj o,
				ObjStack o_, ExprStream e, ExeEnv env){
			if(o == BoolObj.TRUE) return true;
			if(o == BoolObj.FALSE) return false;
			if(o instanceof CodeObj){
				return istrue(((CodeObj)o).getCode()
						.eval(o_, env, e), 
						o_, e, env);
			}
			return false;
		}

		@Override
		public Obj exec(ObjStack o, ExprStream e, ExeEnv env){
			Obj prev = o.pop();
			if(prev instanceof ListObj){
				ListObj c = (ListObj)prev;
				for(int i = 0; i * 2 < c.size(); i++){
					if(istrue(c.get(i * 2), o, e, env)){
						return CodeObj.exec_if_can(
							c.get(i * 2 + 1), 
							o, e, env);
					}
				}
				if(c.size() % 2 == 1){
					return CodeObj.exec_if_can(
						c.get(c.size() -1), o, e, env);
				}
				return NullObj.nul;
			}
			return NullObj.nul;
		}
		
	};

	public static final Func while_loop = new Func(){

		@Override
		public String sname(){
			return "while";
		}
		
		

		@Override
		public Obj exec(ObjStack stk, ExprStream e, ExeEnv env){
			Obj p = stk.pop();
			if(p instanceof ListObj){
				ListObj lst = (ListObj) p;
				if(lst.size() == 2){
					Obj last = stk.peek();
					Obj cond = lst.get(0);
					Obj bod = lst.get(1);
					while(BoolObj.istrue(cond,
							stk, e, env)){
						last = CodeObj.exec_if_can(
							bod, stk, e, env);
					}
					return last;
				}
			}
			System.err.println("bad loop");
			return null;
		}
		
	};
	
}
