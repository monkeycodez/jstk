package org.jstk.run;

import org.jstk.util.FileLocator;

public final class TMain{

	private TMain(){}
	
	public static void main(String[] args){
		FileLocator.add_all_read_dirs(".");
		ScriptRunner.run_file("src/script/example.stk");
	}

}
