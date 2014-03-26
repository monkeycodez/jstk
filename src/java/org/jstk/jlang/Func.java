package org.jstk.jlang;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ObjStack;
import org.jstk.parse.expr.DotExpr;
import org.jstk.parse.expr.NameExpr;

public abstract class Func extends Obj{

	public abstract String sname();

	public abstract Obj exec(ObjStack ostk, ExeEnv env);

	public static Obj exec_if_can(final Obj o, final ObjStack stk,
		final ExeEnv env){
		if(o instanceof Func){
			return ((Func) o).exec(stk, env);
		}
		return o;
	}

	public static final Func print = new Func(){

		@Override
		public String sname(){
			return "print";
		}

		@Override
		public Obj exec(final ObjStack o, final ExeEnv env){
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
		public Obj exec(final ObjStack o, final ExeEnv env){
			System.out.println(o.pop());
			return NullObj.nul;
		}

	};

	public static final Func ret = new Func(){

		@Override
		public String sname(){
			return "ret";
		}

		@Override
		public Obj exec(final ObjStack o, final ExeEnv env){
			env.fret();
			return o.pop();
		}

	};

	public static final Func exit = new Func(){

		@Override
		public String sname(){
			return "exit";
		}

		@Override
		public Obj exec(final ObjStack o, final ExeEnv env){
			System.exit(0);
			return NullObj.nul;
		}

	};

	public static final Func eval = new Func(){

		@Override
		public String sname(){
			return "eval";
		}

		@Override
		public Obj exec(final ObjStack o, final ExeEnv env){
			final Obj prev = o.pop();
			if(prev instanceof CodeObj){
				final CodeObj c = (CodeObj) prev;
				return c.getCode().eval(o, env);
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
		public Obj exec(final ObjStack stk, final ExeEnv env){
			final Obj p = stk.pop();
			if(p instanceof CodeObj){
				final CodeObj c = (CodeObj) p;
				if(c.getCode() instanceof NameExpr){
					final Obj val = stk.pop();
					final NameExpr n =
						(NameExpr) c.getCode();
					env.set_local(n.getName(), val);
					return val;
				}else if(c.getCode() instanceof DotExpr){
					return ((DotExpr) c.getCode()).set(stk,
						env);
				}
			}
			throw new JSTKRuntimeException(
				"second arg to set is not a coderef");
		}

	};

	public static final Func setl = new Func(){

		@Override
		public String sname(){
			return "setl";
		}

		@Override
		public Obj exec(final ObjStack stk, final ExeEnv env){
			final Obj p = stk.pop();
			final Obj val = stk.pop();
			if(p instanceof CodeObj){
				final CodeObj c = (CodeObj) p;
				if(c.getCode() instanceof NameExpr){
					final NameExpr n =
						(NameExpr) c.getCode();
					env.set_local_new(n.getName(), val);
					return val;
				}
			}
			throw new JSTKRuntimeException(
				"second arg to set is not a coderef");
		}

	};

	public static final Func try_f = new Func(){

		@Override
		public String sname(){
			return "try";
		}

		@Override
		public Obj exec(final ObjStack stk, final ExeEnv env){
			final Obj l = stk.pop();
			Obj ret = null;
			if(l instanceof ListObj){
				final ListObj lst = (ListObj) l;
				if(lst.size() == 2){
					try{
						ret =
							CodeObj.exec_if_can(
								lst.get(0),
								stk, env);
					}catch(final JSTKRuntimeException ex){
						stk.push(ex.toExceptObj());
						ret =
							CodeObj.exec_if_can(
								lst.get(1),
								stk, env);
					}
					return ret;
				}
			}
			throw new JSTKRuntimeException(
				"argument to try must be a list " + "of size 2");
		}

	};

	public static final Func cond = new Func(){

		@Override
		public String sname(){
			return "cond";
		}

		private boolean istrue(final Obj o, final ObjStack o_,
			final ExeEnv env){
			if(o == BoolObj.TRUE){
				return true;
			}
			if(o == BoolObj.FALSE){
				return false;
			}
			if(o instanceof CodeObj){
				return istrue(
					((CodeObj) o).getCode().eval(o_, env),
					o_, env);
			}
			return false;
		}

		@Override
		public Obj exec(final ObjStack o, final ExeEnv env){
			final Obj prev = o.pop();
			if(prev instanceof ListObj){
				final ListObj c = (ListObj) prev;
				for(int i = 0; i * 2 < c.size(); i++){
					if(istrue(c.get(i * 2), o, env)){
						return CodeObj.exec_if_can(
							c.get(i * 2 + 1), o,
							env);
					}
				}
				if(c.size() % 2 != 0){
					return CodeObj.exec_if_can(
						c.get(c.size() - 1), o, env);
				}
				return BoolObj.FALSE;
			}
			throw new JSTKRuntimeException(
				"cond function not given a list");
		}

	};

	public static final Func while_loop = new Func(){

		@Override
		public String sname(){
			return "while";
		}

		@Override
		public Obj exec(final ObjStack stk, final ExeEnv env){
			final Obj p = stk.pop();
			if(p instanceof ListObj){
				final ListObj lst = (ListObj) p;
				if(lst.size() == 2){
					Obj last = stk.peek();
					final Obj cond = lst.get(0);
					final Obj bod = lst.get(1);
					while(BoolObj.istrue(cond, stk, env)){
						last =
							CodeObj.exec_if_can(
								bod, stk, env);
					}
					return last;
				}
				throw new JSTKRuntimeException(
					"while list size is not 2");
			}
			throw new JSTKRuntimeException(
				"while function not given a list");
		}

	};

}
