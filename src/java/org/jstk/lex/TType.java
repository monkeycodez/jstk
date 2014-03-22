package org.jstk.lex;

import java.util.HashSet;
import java.util.Set;

public enum TType{
	LPAREN('('),
	RPAREN('\u0029'),
	COMMA(','),
	DOT('.'),
	LBRACE('{'),
	RBRACE('}'),
	LBRACK('['),
	RBRACK(']'),
	BQOUTE('`'),
	OCTO('#'),
	AT('@'),
	COLON(':'),
	SCOLON(';'),
	SQUOTE('\''),
	DQUOTE('\"'),
	BSLASH('\\'),
	IDEN(null),
	KWORD(null),
	TAG(null),
	STRING(null),
	EOF(null);

	public static final Set<String> keywds = new HashSet<>();
	

	public Character punct(){
		return this.punct;
	}

	private final Character punct;

	TType(Character c){
		punct = c;
	}
}
