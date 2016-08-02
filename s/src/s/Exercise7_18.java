package s;

public class Exercise7_18 {
	public static void action(Robot r){
		
		
		
		if(r instanceof DanceRobot){		//받아온 r의 멤버가 ~의 인스턴스냐? 
			DanceRobot dr = new DanceRobot();
			dr.dance();
		}
		
		if(r instanceof SingRobot){
			SingRobot sr = new SingRobot();
			sr.sing();
		}
		if(r instanceof DrawRobot){
			DrawRobot wr = new DrawRobot();
			wr.draw();
		}
		
		
		
	}
	
	public static void main(String[] args){
		Robot dr= new DanceRobot();
		Robot sr= new SingRobot();
		Robot wr= new DrawRobot();
		Robot robot[] ={dr ,sr, wr};
		
		for(int i=0; i< robot.length; i++){
			Exercise7_18.action(robot[i]);	
				//같은 클래스이기 때문에 Exercise7_18. 안써도 된다.
			
		}
	}
}

class Robot{}

class DanceRobot extends Robot{
	void dance(){
		System.out.println("춤을 춤");
	}
}

class SingRobot extends Robot{
	void sing(){
		System.out.println("노래를 합니다.");
	}
}

class DrawRobot extends Robot{
	void draw(){
		System.out.println("그림을 그립니다.");
	}
}