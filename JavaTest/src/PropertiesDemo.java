import java.lang.*;
import java.util.Properties;

public class PropertiesDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = System.getProperties();
		for (Object obj : properties.keySet()) {
			String proString = (String) properties.get(obj);
			System.out.println("----->" + proString);
			
		}

	}

}

/*
 * foreach������ʽ��
 * 
 * for(Ԫ������t Ԫ�ر���x : ��������obj){
 * 
 * ������x��java���;
 * 
 * }
 */
