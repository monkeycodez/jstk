package org.jstk.jlang;

public class JavaObj extends Obj {

	private final Object obj;

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return super.type();
	}

	@Override
	public Obj __getattr__(final String attr) {
		// TODO Auto-generated method stub
		return super.__getattr__(attr);
	}

	@Override
	public Obj __setattr__(final String attr, final Obj val) {
		// TODO Auto-generated method stub
		return super.__setattr__(attr, val);
	}

	@Override
	public Obj __eq__(final Obj o) {
		// TODO Auto-generated method stub
		return super.__eq__(o);
	}

	@Override
	public Obj __ne__(final Obj o) {
		// TODO Auto-generated method stub
		return super.__ne__(o);
	}

	public JavaObj(final Object obj) {
		super();
		this.obj = obj;
	}

}
