package s;
class Speaker{
	private int volumeRate;
	
	public void showCurrentState(){
		System.out.println("볼륨 크기:"+volumeRate);
		
	}
	public void setVolume(int vol){
		volumeRate=vol;
	}
}//Speaker class
class BaseEnSpeaker extends Speaker{
	private int baseRate;
	
	//showCurrentState메소드를 오버라이딩하시오.
	//추가할 것은 출력문 System.out.printl("베이스 크기: "+baseRate);
	public void showCurrentState(){
		System.out.println("베이스 크기:"+baseRate);
	}
	//이게 
	
	public void setBaseRate(int base){
		baseRate=base;
	}
}//BAseEnSpeaker class

public class Overriding0712copy {
	public static void main(String[] args) {
		//위의 두 객체 중 자손객ㄱ체를 생성한 뒤 변수 bs에 저장하시오.
		
		BaseEnSpeaker bs = new BaseEnSpeaker();
			//그럼 아빠가 자손 그거 만드는건? 
		bs.setVolume(10);
		bs.setBaseRate(20); 
		bs.showCurrentState();
	}
}


//아빠가 아들 쓰는거.
//아들은 상속받는게 구현부는 하나도 안 받아오는건가

//다형성
//싱글턴

