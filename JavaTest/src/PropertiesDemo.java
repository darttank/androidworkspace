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
 * foreach的语句格式：
 * 
 * for(元素类型t 元素变量x : 遍历对象obj){
 * 
 * 引用了x的java语句;
 * 
 * }
 */
