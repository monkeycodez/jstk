package org.jstk.run;

import org.jstk.lex.*;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Parser;
import org.jstk.util.FileLocator;

public class TMain{

	public static void main(String args[]){
		FileLocator.add_all_read_dirs(".");
		Lexer l = new Lexer(
			FileLocator.get_read_file("src/script/example.stk"));
		Parser p = new Parser(l);
		p.parseAll().eval(null, new ExeEnv(), null);
	}

}
