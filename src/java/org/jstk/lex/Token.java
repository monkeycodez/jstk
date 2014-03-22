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
		switch(type){
			case IDEN:
				return "IDEN: <"+text+">";
			case STRING:
				return "STRING: <"+text+">";
			default:
				return type.name();
		}
	}

}
