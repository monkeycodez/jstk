package org.jstk.parse.expr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jstk.jlang.Func;
import org.jstk.jlang.Obj;
import org.jstk.lex.*;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;

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
	
	public String getName(){
		return name;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env, ExprStream str){
		Obj o = env.get(name);
		if(o instanceof Func){
			return ((Func)o).exec(stk, str, env);
		}else if(o == null){
			System.err.println("No name found");
			System.exit(-1);
		}
		return o;
	}
}