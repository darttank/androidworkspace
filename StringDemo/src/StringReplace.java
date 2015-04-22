
public class StringReplace {
	public static void main(String[] args) {
		String phoneNum="1795112345";
		phoneNum = phoneNum.replace("17951", "");
		System.out.println(phoneNum);

	}

	public static Boolean ContainsAny(){
		
		String phoneNum="1795112345";
		String IpNum="17951";
		
		return phoneNum.contains(IpNum);
		
	}

}
