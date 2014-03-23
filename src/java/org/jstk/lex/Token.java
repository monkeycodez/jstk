package org.jstk.lex;

public class Token{
	
	private final TType type;
	private final String text;
	private final int lineno;

	public Token(TType type, String text, int lineno){
		this.type = type;
		this.text = text;
		this.lineno = lineno;
	}

	
	/**
	 * @return the type
	 */
	public TType getType(){
		return type;
	}


	
	/**
	 * @return the text
	 */
	public String getText(){
		return text;
	}

	public boolean isAtom(){
		return type.punct() == null;
	}

	@Override
	public String toString(){
		switch(type){
			case IDEN:
				return "IDEN: <"+text+">";
			case STRING:
				return "STRING: <"+text+">";
			default:
				return type.name();
		}
	}


	public int getLineno(){
		return lineno;
	}

}
