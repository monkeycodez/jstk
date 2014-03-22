package org.jstk.util;

public final class LinkedStack<E>{

	private LinkedStack<E> next = null;
	private E dat = null;

	public LinkedStack(){}

	public LinkedStack(E bot){
		dat  = bot;
	}

	public final void push(E dat){
		if(next == null){
			next = new LinkedStack<>(dat);
		}else{
			LinkedStack<E> nnode = new LinkedStack<E>(dat);
			nnode.next = next;
			next = nnode;
		}
	}

	public final E pop(){
		if(this.next != null){
			LinkedStack<E> e = this.next;
			this.next = null;
			return e.dat;
		}
		return this.dat;
	}

	public final E peek(){
		if(this.next != null){
			return this.next.dat;
		}
		return dat;
	}

	public final boolean is_empty(){
		return this.next == null && this.dat == null;
	}

	//Tests
	/*
	public static void main(String args[]){
		int i1 = 0, i2 = 0;
		long a1 = 0, a2 = 0;
		for(int i = 0; i < nr; i++){
			long t1 = System.nanoTime();
			test1();
			t1 = System.nanoTime() - t1;
			System.out.print("LS1 time: " + t1);
			a1 += t1 / nr;
			long t2 = System.nanoTime();
			test2();
			t2 = System.nanoTime() - t2;
			System.out.print("\tLS2 time: " + t2);
			a2 += t2 / nr;
			if(t1 < t2){ 
				System.out.println("\tLS1");
				i1++;
			}else{
				i2++;	
				System.out.println("\tLS2");
			}
		}
		System.out.println("RES:\t"+i1+"\t"+i2);
		System.out.println("AVG:\t"+a1+"\t"+a2);
	}
	static final int nt = 100000, nr = 500;
	static String obj = " ";

	public static void test1(){
		LinkedStack<String> l = new LinkedStack<String>(obj);
		for(int i = 0; i < nt; i++){
			l.push(obj);
		}
		for(int i = 0; /*!l.is_empty() && i < nt; i++){
			l.peek();
			l.pop();
		}
	}
	public static void test2(){
		LinkedStack2<String> l = new LinkedStack2<String>(obj);
		for(int i = 0; i < nt; i++){
			l.push(obj);
		}
		for(int i = 0; /*!l.is_empty() && i < nt; i++){
			l.peek();
			l.pop();
		}
	}*/
}
