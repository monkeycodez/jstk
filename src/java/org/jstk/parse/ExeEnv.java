package org.jstk.parse;

import java.util.*;
import org.jstk.jlang.Func;
import org.jstk.jlang.Obj;
import org.jstk.jlang.SMath;

public class ExeEnv{
	
	private Map<String, Obj> globals = new HashMap<>();

	public ExeEnv(){
		//Defaults
		add_global_func(SMath.add);
		add_global_func(SMath.sub);
		add_global_func(SMath.mult);
		add_global_func(SMath.div);
		add_global_func(Func.print);
		add_global_func(Func.println);

	}
	
	public void add_global_func(Func f){
		globals.put(f.sname(), f);
	}
	
	public Obj get(String str){
		return globals.get(str);
	}
}
