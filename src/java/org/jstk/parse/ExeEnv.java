package org.jstk.parse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jstk.jlang.BoolObj;
import org.jstk.jlang.Func;
import org.jstk.jlang.JSTKClass;
import org.jstk.jlang.JSTKRuntimeException;
import org.jstk.jlang.NullObj;
import org.jstk.jlang.Obj;
import org.jstk.jlang.SMath;
import org.jstk.jlang.StkFunc;

public final class ExeEnv{

	private final Map<String, __ref> globals = new HashMap<>();

	private final LinkedList<__frame> vstack = new LinkedList<>();

	private int full_stacks = 1;

	private boolean ret = false;

	private class __ref{

		private Obj ref;

		__ref(final Obj r){
			ref = r;
		}

		@Override
		public String toString(){
			return ref.toString();
		}
	}

	private class __frame{

		private Map<String, __ref> vframe;

		private String nframe;

		private int line_exit = -1;

		private final int frameno = full_stacks;

		private boolean full_frame = true;

		__frame(final Map<String, __ref> fframe, final String nfr){
			nframe = nfr;
			vframe = new HashMap<>(fframe);
		}

		__frame(){
		}

		@Override
		public String toString(){
			return "<FRAME> " + nframe + "@" + line_exit + " "
				+ frameno;
		}
	}

	public ExeEnv(){
		// Defaults
		final __frame gbl = new __frame();
		gbl.nframe = "<GLOBAL>";
		gbl.vframe = globals;
		vstack.push(gbl);
		set_local("true", BoolObj.TRUE);
		set_local("false", BoolObj.FALSE);
		set_local("null", NullObj.nul);

		add_global_func(SMath.add);
		add_global_func(SMath.sub);
		add_global_func(SMath.mult);
		add_global_func(SMath.div);
		add_global_func(SMath.mod);
		add_global_func(SMath.inc);
		add_global_func(SMath.dec);

		add_global_func(SMath.eq);
		add_global_func(SMath.ne);
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
		add_global_func(Func.ret);
		add_global_func(Func.setl);
		add_global_func(Func.while_loop);
		add_global_func(Func.try_f);

		add_global_func(JSTKClass.defclass);

		add_global_func(StkFunc.defun);

	}

	public void add_global_func(final Func f){
		globals.put(f.sname(), new __ref(f));
	}

	public Obj get(final String str){
		final __ref r = vstack.peek().vframe.get(str);
		if(r == null){
			throw new JSTKRuntimeException("No name " + str
				+ " found");
		}
		return r.ref;
	}

	public void set_local(final String name, final Obj val){
		final __ref r = vstack.peek().vframe.get(name);
		if(r != null){
			r.ref = val;
			return;
		}
		// System.err.println("Setting: "+name+"@"+val);
		vstack.peek().vframe.put(name, new __ref(val));
	}

	public void set_local_new(final String name, final Obj val){
		vstack.peek().vframe.put(name, new __ref(val));
	}

	public void push_frame(final String fname){
		// System.err.println("Push frame");
		full_stacks++;
		final __frame nframe = new __frame(globals, fname);
		vstack.push(nframe);
	}

	public void push_half_fram(){
		push_half_frame("<ERROR>");
	}

	public void push_half_frame(final String fname){
		// System.err.println("Push half frame");
		full_stacks++;
		final __frame old = vstack.peek();
		final __frame nframe = new __frame(old.vframe, fname);
		nframe.full_frame = false;
		vstack.push(nframe);
	}

	public void pop_frame(){
		// System.err.println("Pop half frame");
		full_stacks--;
		vstack.pop();
	}

	public void __print_frame(){
		System.err.println(vstack.peek().toString());
	}

	public void set_cline(final int c){
		vstack.peek().line_exit = c;
	}

	public int get_cline(){
		return vstack.peek().line_exit;
	}

	public int get_cframe(){
		return full_stacks;
	}

	public String get_sframe(){
		return vstack.peek().nframe;
	}

	public List<Integer> get_line_trace(final int frameno){
		final LinkedList<Integer> trace = new LinkedList<>();
		for(final __frame fr : vstack){
			if(fr.frameno < frameno){
				break;
			}
			if(fr.full_frame){
				trace.addLast(fr.line_exit);
			}
		}
		return trace;
	}

	public List<String> get_name_trace(final int frameno){
		final LinkedList<String> trace = new LinkedList<>();
		for(final __frame fr : vstack){
			if(fr.frameno == frameno - 1){
				break;
			}
			if(fr.full_frame){
				trace.addLast(fr.nframe);
			}
		}
		return trace;
	}

	public boolean is_ret(){
		return ret;
	}

	public void fret(){
		ret = !ret;
	}
}
