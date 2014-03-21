package org.jstk.lex;

public class Token{
	
	private final TType type;
	private final String text;

	public Token(TType type, String text){
		this.type = type;
		this.text = text;
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
		if(type != TType.IDEN){
			return type.toString();
		}
		return "IDEN: <"+text+">";
	}

}
