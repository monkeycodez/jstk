package org.jstk.parse;

import java.util.*;
import org.jstk.jlang.BoolObj;
import org.jstk.jlang.Func;
import org.jstk.jlang.NullObj;
import org.jstk.jlang.Obj;
import org.jstk.jlang.SMath;
import org.jstk.jlang.StkFunc;
import org.jstk.util.LinkedStack;

public class ExeEnv{
	
	private Map<String, __ref> globals = new HashMap<>();

	private LinkedList<Map<String, __ref>> vstack = 
			new LinkedList<Map<String, __ref>>();
			
	private class __ref{
		Obj ref;
		__ref(Obj r){ ref = r; }
		public String toString(){
			return ref.toString();
		}
	}
	
	public ExeEnv(){
		//Defaults
		vstack.push(globals);
		set_local("true", BoolObj.TRUE);
		set_local("false", BoolObj.FALSE);
		set_local("null", NullObj.nul);

		add_global_func(SMath.add);
		add_global_func(SMath.sub);
		add_global_func(SMath.mult);
		add_global_func(SMath.div);
		add_global_func(SMath.inc);
		add_global_func(SMath.dec);
		
		add_global_func(SMath.eq);
		add_global_func(SMath.lt);
		add_global_func(SMath.gt);
		add_global_func(SMath.le);
		add_global_func(SMath.ge);

		add_global_func(Func.print);
		add_global_func(Func.println);
		add_global_func(Func.exit);
		add_global_func(Func.eval);
		add_global_func(Func.cond);
		add_global_func(Func.set);
		add_global_func(Func.while_loop);
		
		add_global_func(StkFunc.defun);

	}
	
	public void add_global_func(Func f){
		globals.put(f.sname(), new __ref(f));
	}
	
	public Obj get(String str){
		__ref r = vstack.peek().get(str);
		if(r == null){
			System.err.println("No "+str);
		}
		return vstack.peek().get(str).ref;
	}
	
	public void set_local(String name, Obj val){
		__ref r = vstack.peek().get(name);
		if(r != null){
			r.ref = val;
			return;
		}
	//	System.err.println("Setting: "+name+"@"+val);
		vstack.peek().put(name, new __ref(val));
	}
	
	public void set_local(Obj old, Obj val){
		Collection<__ref> rf = vstack.peek().values();
		for(__ref lf: rf){
			if(lf.ref == old){
				lf.ref = val;
			}
		}
	}
	
	
	
	public void push_frame(){
	//	System.err.println("Push frame");
		HashMap<String, __ref> nframe = new HashMap<>();
		nframe.putAll(globals);
		vstack.push(nframe);
	}
	
	public void push_half_fram(){
	//	System.err.println("Push half frame");
		HashMap<String, __ref> nframe = new HashMap<>(vstack.peek());
		nframe.putAll(vstack.peek());
		vstack.push(nframe);
	}
	
	public void pop_frame(){
	//	System.err.println("Pop half frame");
		vstack.pop();
	}
	
	public void __print_frame(){
		System.err.println(vstack.peek().toString());
	}
}
