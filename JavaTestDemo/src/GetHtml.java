import java.io.UnsupportedEncodingException;


public class GetHtml {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String getResult =  getHtml("http://ear.duomi.com/?p=310336");
		System.out.println(getResult.toString());
	}
	
	/*
	  * 
	  * */
	 public static   String getHtml(String urlString) {  
	  try {
	   StringBuffer html = new StringBuffer();  
	   java.net.URL url = new java.net.URL(urlString);  //��� String ��ʾ��ʽ���� URL ����
	   java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();// ����һ�� URLConnection �������ʾ�� URL �����õ�Զ�̶�������ӡ�
	   java.io.InputStreamReader isr = new java.io.InputStreamReader(conn.getInputStream());//���شӴ˴򿪵����Ӷ�ȡ����������
	   java.io.BufferedReader br = new java.io.BufferedReader(isr);//����һ��ʹ��Ĭ�ϴ�С���뻺����Ļ����ַ���������
	   
	   String temp;
	   while ((temp = br.readLine()) != null) {  //���ж�ȡ�����
	    if(!temp.trim().equals("")){
	     html.append(temp).append("\n");  //����ÿ�к���
	    }
	   }
	    br.close();   //�ر�
	    isr.close();  //�ر�
	   return html.toString();   //���ش���������ݵ��ַ��ʾ��ʽ��
	  } catch (Exception e) {
	   e.printStackTrace();
	   return null;
	  }
	 }


}
