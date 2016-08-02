package s;

class Person{
	private int perID;
	private int milID;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	
	public Person(int perID, int milID, int birthYear, int birthMonth, int birthDay){  //int 변수 5개를 받아와서 Person클래스 변수에 저장하는 생성자를 만드시오
		 this.perID= perID ;
		 this.milID= milID  ;
		 this.birthYear=birthYear  ;
		 this.birthMonth=birthMonth;
		 this.birthDay =birthDay;
		
		
	}
	
	public Person(int pID, int bYear, int bMonth, int bDay){//int 변수 4개를 받아와서 바로 위의 Person 생성자에 전달할 수 있도록
					 											//새로운 생성자를 만드시오
				
		/*Person(int,int,int,int,int)=this;  내가 쓴 답.*/
		this(pID,0, bYear,bMonth,bDay);		
	}
	
	public void showInfo(){
		System.out.println("민번 : "+perID);
		System.out.println("생년월일 : "+birthYear+"/"+birthMonth+"/"+birthDay);
		if(milID != 0){
			System.out.println("군번: "+milID+"\n");
		}else{
			System.out.println("군과 관계 없음.\n");
		}
	}
}

public class Overloading0712copy {
	public static void main(String[] args){
		Person man= new Person(951203, 990102, 1995, 12, 3); //int 변수 5개를 주면서 Perso객체를 생성하시오	}
		Person woman= new Person(991107,1999,11,7); //int 변수Person 4개를 주면서Perso객체를 생성하시오
		man.showInfo();
		woman.showInfo();
	}
}

