package s;

public class LastIndexOf {
	public static void main(String[] args) {
		String fullPath = "c:\\coool\\work\\siwon.java";
		String path = " ";
		String fileName="";
		
		int xx = fullPath.lastIndexOf("\\");  //뒤에서 부터 찾아서 그 문자가 시작하는 index를 구한다.
		fileName = fullPath.substring(xx+1);	//매개변수 하나면 ~ 그 인덱스 뒤로 잘라서 가져온다.
		path = fullPath.substring(0,xx );
		
		System.out.println("fullPath: "+fullPath);
		System.out.println("path : "+path);
		System.out.println("fileName : "+fileName);
	}
}
