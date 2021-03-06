package org.jstk.lex;

import java.io.*;

public class LexReader{

	public static final char EOF = 0xFFFF;

	private static final char  NONE = 0xEFFF;

	private BufferedReader rdr;

	private char pb = NONE;

	private int lineno = 0;
	
	public LexReader(InputStream s){
		rdr = new BufferedReader(new InputStreamReader(s));
	}
	public LexReader(String s){
		rdr = new BufferedReader(new StringReader(s));
	}
	


	public char peek(){
		if(pb != NONE){
			return pb;
		}else{
			try{
				int t = rdr.read();
				if(t == -1){
					rdr.close();
					pb = EOF;
					return EOF;
				}else if(t == '\n'){
					lineno++;
				}
				pb = (char) t;
				return pb;
			}catch(IOException e){
				//Something bad...
				pb = EOF;
				return EOF;
			}
		}
	}

	public char read(){
		if(pb != NONE){
			char t = pb;
			pb = NONE;
			return t;
		}
		try{
			int t = rdr.read();
			if(t == -1){
				rdr.close();
				pb = EOF;
				return EOF;
			}else if(t == '\n'){
				lineno++;
			}
			return (char) t;
		}catch(IOException e){
			//Something bad...
			pb = EOF;
			return EOF;
		}
	}

	public boolean hasNext(){
		return pb != EOF;
	}
	
	public void push(char c){
		pb = c;
	}
	
	public int get_line(){
		return lineno;
	}

}
