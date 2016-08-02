package s;

public class IndexOf_length {
	public static int count(String src, String target){
		int count =0;
		int pos = 0;
		
/*		while(true){
		pos=src.indexOf(target);	//못찾으면 -1 반환한다.
				System.out.println(pos);
		target=src.indexOf(pos);
				System.out.println(target);
		if(pos==-1){
			break;
		}
		count++;
				System.out.println(count);
	}
		*/
		
		while(true){
			pos = src.indexOf(target,pos);//5
			
			if(pos!=-1){
				count++; //1
				pos +=target.length();
			}else{
				break;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(count("12345AB12AB345AB","AB"));
		System.out.println(count("12345","AB"));
	}
}
