package org.jstk.jlang;

public class LObj extends Obj{

	public final long num;

	public LObj(long num){
		this.num = num;
	}
	
	public String toString(){
		return num+"";
	}
}
