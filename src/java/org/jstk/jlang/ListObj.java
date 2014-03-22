package org.jstk.jlang;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ListObj extends Obj implements Iterable<Obj>{
	
	public Iterator<Obj> iterator(){
		return list.iterator();
	}

	private List<Obj> list;
	
	public ListObj(List<Obj> l){
		list = l;
	}

	public int size(){
		return list.size();
	}

	public boolean isEmpty(){
		return list.isEmpty();
	}

	public boolean contains(Object o){
		return list.contains(o);
	}

	public boolean add(Obj e){
		return list.add(e);
	}

	public boolean remove(Object o){
		return list.remove(o);
	}

	public void clear(){
		list.clear();
	}

	public boolean equals(Object o){
		return list.equals(o);
	}

	public int hashCode(){
		return list.hashCode();
	}

	public Obj get(int index){
		return list.get(index);
	}

	public Obj set(int index, Obj element){
		return list.set(index, element);
	}

	public Obj remove(int index){
		return list.remove(index);
	}

	public int indexOf(Object o){
		return list.indexOf(o);
	}
	
	public String toString(){
		return Arrays.toString(list.toArray());
	}
	
}
