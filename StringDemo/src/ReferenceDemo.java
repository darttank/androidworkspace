import java.lang.ref.ReferenceQueue;


public class ReferenceDemo {
	
	public static boolean isRun = true;
	public static void main(String[] args) {
		String abc = new String ("abc");
		System.out.println(abc.getClass() +"@"+ abc.hashCode() );
	    final ReferenceQueue referenceQueue = new ReferenceQueue<String>();
	}

}
