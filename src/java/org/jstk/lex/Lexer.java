package org.jstk.lex;

import java.io.InputStream;
import java.util.*;

public class Lexer implements Iterable<Token>, Iterator<Token>{

	private final HashMap<Character, TType> punct = new HashMap<>();

	private final LexReader rdr;

	private Token peek;

	{
		for(TType t : TType.values()){
			Character p = t.punct();
			if(p != null){
				this.punct.put(p, t);
			}
		}
	}

	public Lexer(String in) {
		rdr = new LexReader(in);
	}

	public Lexer(InputStream in) {
		rdr = new LexReader(in);
	}

	private String getw(){
		StringBuilder b = new StringBuilder();
		char n = rdr.peek();
		while(!punct.containsKey(n) && n != LexReader.EOF &&
				!Character.isWhitespace(n)){
			rdr.read();
			b.append(n);
			n = rdr.peek();
		}
		return b.toString();
	}

	private String gets(){
		StringBuilder b = new StringBuilder();
		char n = rdr.peek();
		if(n == '\"'){
			return "";
		}
		rdr.read();
		while((n != '\"') && n != LexReader.EOF){
			b.append(n);
			n = rdr.read();
			if(n == '\"' && b.charAt(b.length() - 1) == '\\'){
				b.append(n);
				n = rdr.peek();
			}
		}
		String s = b.toString();
		s = s.replace("\\\"", "\"");
		return s;
	}

	private void do_comment(){
		char c;
		for(;;){
			c = rdr.read();
			if(c == '*' && rdr.peek() == '/'){
				rdr.read();
				return;
			}
		}
	}

	@Override
	public Token next(){
		peek = null;
		while(rdr.hasNext()){
			char c = rdr.read();
			if(punct.containsKey(c)){
				if(c == ':'){
					String s = getw();
					return new Token(TType.TAG, ":" + s,
							rdr.get_line());
				}else if(c == '\"'){
					String s = gets();
					return new Token(TType.STRING, s,
							rdr.get_line());
				}
				return new Token(punct.get(c),
						Character.toString(c),
						rdr.get_line());
			}else if(!Character.isWhitespace(c) &&
					c != LexReader.EOF){
				if(c == '/' && rdr.peek() == '*'){
					rdr.read();
					do_comment();
					return next();
				}
				rdr.push(c);
				String s = getw();
				return new Token(TType.IDEN, s, rdr.get_line());
			} // Whitespace, ignore
		}
		return new Token(TType.EOF, "", rdr.get_line());
	}

	public Token peek(){
		if(peek == null){
			peek = next();
			return peek;
		}else{
			return peek;
		}
	}

	@Override
	public boolean hasNext(){
		return rdr.hasNext();
	}

	@Override
	public void remove(){
		throw new UnsupportedOperationException("Not gonna use");
	}

	@Override
	public Iterator<Token> iterator(){
		return this;
	}

}
