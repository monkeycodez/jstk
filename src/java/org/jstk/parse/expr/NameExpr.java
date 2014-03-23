package org.jstk.parse.expr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jstk.jlang.Func;
import org.jstk.jlang.Obj;
import org.jstk.lex.*;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Expr;
import org.jstk.parse.ObjStack;

public final class NameExpr implements Expr{

	private static String num_s = "(:?-|)\\d+";

	private static Pattern num = Pattern.compile(num_s);

	public static Expr create(Token t){
		Matcher m = num.matcher(t.getText());
		if(m.matches()){
			return new NumExpr(t.getText(), t.getLineno());
		}
		return new NameExpr(t);
	}

	private final String name;
	private final int lineno;
	
	public NameExpr(Token t) {
		this.lineno = t.getLineno();
		this.name = t.getText();
	}
	
	public String getName(){
		return name;
	}

	@Override
	public Obj eval(ObjStack stk, ExeEnv env){
		Obj o = env.get(name);
		if(o instanceof Func){
			return ((Func)o).exec(stk, env);
		}else if(o == null){
			System.err.println("No name found");
			System.exit(-1);
		}
		return o;
	}

	@Override
	public int lineno(){
		return lineno;
	}
}
