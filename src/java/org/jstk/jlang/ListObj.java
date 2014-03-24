package org.jstk.jlang;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jstk.parse.ExeEnv;
import org.jstk.parse.ObjStack;

public class ListObj extends Obj implements Iterable<Obj> {

	private final List<Obj> list;

	private final Func get = new Func() {

		@Override
		public String sname() {
			return "get";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env) {
			final Obj idx = ostk.pop();
			try {
				return list.get((int) ((LObj) idx).get_val());
			} catch (final Exception ex) {

			}
			throw new JSTKRuntimeException("ERROR: bad list index: " + idx);
		}

	};

	private final Func add = new Func() {

		@Override
		public String sname() {
			return "add";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env) {
			final Obj val = ostk.pop();
			try {
				list.add(val);
				return val;
			} catch (final Exception ex) {

			}
			throw new JSTKRuntimeException("ERROR: cannot add " + val
					+ " to list");
		}

	};

	private final Func set = new Func() {

		@Override
		public String sname() {
			return "set";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env) {
			final Obj idx = ostk.pop();
			final Obj val = ostk.pop();
			try {
				return list.set((int) ((LObj) idx).get_val(), val);
			} catch (final Exception ex) {

			}
			throw new JSTKRuntimeException("ERROR: bad list index: " + idx);
		}

	};

	private final Func contains = new Func() {

		@Override
		public String sname() {
			return "in";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env) {
			final Obj idx = ostk.pop();
			try {
				return BoolObj.toObj(list.contains(idx));
			} catch (final Exception ex) {

			}
			throw new JSTKRuntimeException("ERROR: bad list index: ");
		}

	};

	private final Func idx = new Func() {

		@Override
		public String sname() {
			return "idxOf";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env) {
			final Obj idx = ostk.pop();
			try {
				return new LObj(list.indexOf(idx));
			} catch (final Exception ex) {

			}
			throw new JSTKRuntimeException("ERROR: bad list index: ");
		}

	};

	private final Func remove = new Func() {

		@Override
		public String sname() {
			return "remove";
		}

		@Override
		public Obj exec(final ObjStack ostk, final ExeEnv env) {
			final Obj idx = ostk.pop();
			try {
				return list.remove((int) ((LObj) idx).get_val());
			} catch (final Exception ex) {

			}
			throw new JSTKRuntimeException("ERROR: bad list index: ");
		}

	};

	public ListObj(final List<Obj> l) {
		list = l;
	}

	public int size() {
		return list.size();
	}

	@Override
	public Iterator<Obj> iterator() {
		return list.iterator();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(final Object o) {
		return list.contains(o);
	}

	public boolean add(final Obj e) {
		return list.add(e);
	}

	public boolean remove(final Object o) {
		return list.remove(o);
	}

	public void clear() {
		list.clear();
	}

	@Override
	public boolean equals(final Object o) {
		return list.equals(o);
	}

	@Override
	public int hashCode() {
		return list.hashCode();
	}

	public Obj get(final int index) {
		return list.get(index);
	}

	public Obj set(final int index, final Obj element) {
		return list.set(index, element);
	}

	public Obj remove(final int index) {
		return list.remove(index);
	}

	public int indexOf(final Object o) {
		return list.indexOf(o);
	}

	@Override
	public String toString() {
		return Arrays.toString(list.toArray());
	}

	@Override
	public Obj __getattr__(final String attr) {
		switch (attr) {
			case "len":
				return new LObj(list.size());
			case "get":
				return get;
			case "set":
				return set;
			case "add":
				return add;
			case "in":
				return contains;
			case "idxOf":
				return idx;
			case "idx":
				return idx;
			case "remove":
				return remove;
			default:
				return super.__getattr__(attr);
		}
	}

}
