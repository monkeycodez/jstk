package org.jstk.lex;

import java.util.*;

public class Lexer implements Iterable<Token>, Iterator<Token>{

	private final HashMap<Character, TType> punct = new HashMap<>();

	private final LexReader rdr;

	private Token peek;

	{
		for(TType t : TType.values()){
			Character p = t.punct();
			if(p != null)
				this.punct.put(p, t);
		}
	}

	public Lexer(String in) {
		rdr = new LexReader(in);
	}

	private String getw(){
		StringBuilder b = new StringBuilder();
		char n = rdr.peek();
		while(!punct.containsKey(n) && n != LexReader.EOF
				&& !Character.isWhitespace(n)){
			rdr.read();
			b.append(n);
			n = rdr.peek();
		}
		return b.toString();
	}

	@Override
	public Token next(){
		peek = null;
		while(rdr.hasNext()){
			char c = rdr.read();
			if(punct.containsKey(c)){
				if(c == ':'){
					String s = getw();
					return new Token(TType.TAG, s);
				}
				return new Token(punct.get(c),
						Character.toString(c));
			}else if(!Character.isWhitespace(c)){
				rdr.push(c);
				return new Token(TType.IDEN, getw());
			}else{
				// Whitespace, ignore
			}
		}
		return new Token(TType.EOF, "");
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
