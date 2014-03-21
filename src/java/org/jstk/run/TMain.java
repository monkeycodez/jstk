package org.jstk.run;

import org.jstk.lex.*;
import org.jstk.parse.ExeEnv;
import org.jstk.parse.Parser;

public class TMain{

	public static void main(String args[]){
		Lexer l = new Lexer("1 2 + 3 * println");
		Parser p = new Parser(l);
		p.parseAll().eval(null, new ExeEnv(), null);
	}

}
