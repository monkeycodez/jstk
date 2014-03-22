package org.jstk.parse;

import java.util.*;
import org.jstk.jlang.*;

public class ObjStack{
	
	private LinkedList<Obj> stk = new LinkedList<>();
	
	public ObjStack(){
		
	}
	
	public void push(Obj o){
		stk.push(o);
	}
	
	public Obj pop(){
		return stk.poll();
	}
	
	public Obj peek(){
		return stk.peek();
	}
	
	public List<Obj> to_list(){
		List<Obj> nl = new LinkedList<>(stk);
		Collections.reverse(nl);
		return nl;
	}

}
