package test;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class ClassNestTest {
	private Nest nest=new Nest();
	public void callTest() {
		nest.dNest.Test();
	}
	protected void log(Object o) {
		System.out.println(o);
	}
	public Nest getNest(){
		return nest;
	}

	public class Nest{
		private DoubleNest dNest=new DoubleNest();
		public void Test() {
			log(this);
		}
		public DoubleNest getNest2() {
			return dNest;
		}
		public class DoubleNest{
			public void Test() {
				log(this);
			}
		}
	}
}
