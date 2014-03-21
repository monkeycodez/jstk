package org.jstk.lex;

public enum TType{
	LPAREN('('),
	RPAREN('\u0029'),
//	COMMA(','),
/*	EQU('='),
	PLUS('+'),
	MINUS('-'),
	SLASH('/'),
	STAR('*'),
	LARROW('<'),
	RARROW('>'),
	QUESTION('?'),*/
//	PIPE('|'),
	DOT('.'),
	LBRACE('{'),
	RBRACE('}'),
	LBRACK('['),
	RBRACK(']'),
	BQOUTE('`'),
	OCTO('#'),
//	DOLLAR('$'),
	AT('@'),
	COLON(':'),
	SCOLON(';'),
	/*BANG('!'),
	AMPER('&'),
	CARRET('^'),
	PERCENT('%'),*/
	SQUOTE('\''),
	//TILDE('~'),
	BSLASH('\\'),
	IDEN(null),
	KWORD(null),
	TAG(null),
	EOF(null);


	public Character punct(){
		return this.punct;
	}

	private final Character punct;

	TType(Character c){
		punct = c;
	}
}
