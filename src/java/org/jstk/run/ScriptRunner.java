package org.jstk.run;

import java.io.InputStream;
import org.jstk.jlang.JSTKRuntimeException;
import org.jstk.jlang.NullObj;
import org.jstk.lex.Lexer;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.ExprStream;
import org.jstk.parse.ObjStack;
import org.jstk.parse.Parser;
import org.jstk.util.FileLocator;


public final class ScriptRunner{
	
	private ScriptRunner(){}
	
	public static void run_file(String file){
		run_stream(FileLocator.get_read_file(file));
	}
	
	public static void run_stream(InputStream in){
		Lexer l = new Lexer(in);
		Parser p = new Parser(l);
		ObjStack s = new ObjStack();
		s.push(NullObj.nul);
		ExeEnv env = new ExeEnv();
		ExprStream prog = p.parseAll();
		int cframe = env.get_cframe();
		try{
			prog.iterate(s, env);
		}catch(JSTKRuntimeException ex){
			ex.set_frames(cframe, env);
			ex.printStackTrace();
		}
	}

}
