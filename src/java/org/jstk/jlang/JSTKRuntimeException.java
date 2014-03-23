package org.jstk.jlang;

import java.util.List;
import org.jstk.parse.ExeEnv;

public class JSTKRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5811931984068765076L;

	private String trace = "";

	public JSTKRuntimeException(String msg) {
		super(msg);
		trace += msg;
	}

	public void set_frames(int frameno, ExeEnv env){
		List<Integer> itrace = env.get_line_trace(frameno);
		List<String> strace = env.get_name_trace(frameno);
		trace += " at " + strace.get(0) + "(JSTK: " + env.get_cline() +
				")\n\t";
		for(int i = 1; i < itrace.size(); i++){
			trace += " ...at " + strace.get(i) + "(JSTK: " +
					itrace.get(i) + ")\n\t";
		}
	}

	@Override
	public void printStackTrace(){
		System.err.println(trace);
	}
	
	public ExceptObj toExceptObj(){
		return new ExceptObj(this);
	}

}
