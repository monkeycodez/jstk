package org.jstk.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jstk.jlang.Func;
import org.jstk.jlang.Obj;
import org.jstk.lex.*;

public class NameExpr implements Expr{

	private static String num_s = "(:?-|)\\d+";

	private static Pattern num = Pattern.compile(num_s);

	public static Expr create(Token t){
		Matcher m = num.matcher(t.getText());
		if(m.matches()){
			return new NumExpr(t.getText());
		}
		return new NameExpr(t.getText());
	}
	
	private String name;

	public NameExpr(String name) {
		this.name = name;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		Obj o = env.get(name);
		if(o instanceof Func){
			return ((Func)o).exec(stk, str, env);
		}
		return o;
	}
}
