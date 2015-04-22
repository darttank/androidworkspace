
public class ReferentDemo {
	public static void main(String[] args) {
		A a=new A();
		a.b = new B();
		a =null;
	   System.gc();
	}

}

class A{
	B b;
	public void finalize(){
		System.out.println("method A.finalize at " + System.nanoTime());    
	}
	
}

class B{
   public void finalize(){
	   System.out.println("method B.finalize at " + System.nanoTime());    
	}
}