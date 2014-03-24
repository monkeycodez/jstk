package org.jstk.jlang;

import java.util.ArrayList;
import java.util.List;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;
import org.jstk.parse.expr.NameExpr;

public class StkFunc extends Func{

	private static final int list_contains = 3;

	private String name;

	private String[] args;

	private Expr body;

	private boolean macro = false;

	public StkFunc(String name, String[] args, Expr body) {
		super();
		this.name = name;
		this.args = args;
		this.body = body;
	}

	@Override
	public String sname(){
		return name;
	}

	@Override
	public Obj exec(ObjStack ostk, ExeEnv env){
		Obj[] argo = new Obj[args.length];
		for(int i = argo.length - 1; i >= 0; i--){
			argo[i] = ostk.pop();
			if(argo[i] == null){
				System.out.println("ERROR: not enough args");
			}
		}
		if(macro){
			env.push_half_frame(name);
		}else{
			env.push_frame(name);
		}
		for(int i = 0; i < args.length; i++){
			// System.err.println(args[i] + ":"+ argo[i]);
			env.set_local(args[i], argo[i]);
		}
		Obj result = body.eval(ostk, env);
		if(env.is_ret()){
			env.fret();
		}
		env.pop_frame();
		return result;
	}

	public static final Func defun = new Func(){

		@Override
		public String sname(){
			return "defun";
		}

		@Override
		public Obj exec(ObjStack ostk, ExeEnv env){
			Obj o = ostk.pop();
			if(!(o instanceof ListObj)){
				// ERROR
				throw new JSTKRuntimeException(
					"Bad func call, arg not a list");
			}
			ListObj ar = (ListObj) o;
			if(ar.size() < list_contains){
				throw new JSTKRuntimeException(
					"Bad func call, list not long enough");
			}
			// Parse body
			Obj b = ar.get(2);
			if(!(b instanceof CodeObj)){
				return null;
			}
			Expr body = ((CodeObj) b).getCode();
			// Parse name
			Obj n = ar.get(0);
			if(!(n instanceof CodeObj)){
				return null;
			}
			Expr ne = ((CodeObj) n).getCode();
			if(!(ne instanceof NameExpr)){
				return null;
			}
			String name = ((NameExpr) ne).getName();
			// Parse args
			Obj a = ar.get(1);
			if(!(a instanceof ListObj)){
				return null;
			}
			ListObj ae = (ListObj) a;
			List<String> args = new ArrayList<>();
			for(Obj arg : ae){
				if(arg instanceof CodeObj){
					Expr ace = ((CodeObj) arg).getCode();
					if(ace instanceof NameExpr){
						args.add(((NameExpr) ace)
							.getName());
						continue;
					}
				}
				throw new JSTKRuntimeException(
					"Bad arg format, needs"
						+ " to be a coderef");
			}
			StkFunc func = new StkFunc(name,
				args.toArray(new String[1]), body);
			if(ar.size() > list_contains){
				for(int i = list_contains; i < ar.size(); i++){
					Obj ol = ar.get(i);
					if(!(ol instanceof TagObj)){
						throw new JSTKRuntimeException(
							"Bad arg format, needs"
								+ " to be a tag");
					}
					switch(ol.toString()){
						case ":macro":
							func.macro = true;
							break;
						default:
							throw new JSTKRuntimeException(
								"Unknown defun option" +
									ol);
					}
				}
			}
			env.set_local(func.name, func);
			return func;
		}

	};

}
