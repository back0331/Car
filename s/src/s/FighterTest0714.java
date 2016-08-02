package s;

public class FighterTest0714 {
	public static void main(String[] args) {
		Fighter f = new Fighter();
		Unit u = new Unit();
		
		int x = 10;
		int y = 10;
		
		if(f instanceof Unit){
			System.out.println("f는 Unit클래스의 자손입니다.");
		}
		if(f instanceof Fightable){
			System.out.println("f는 Fightable의 클래스의 자손입니다.");
		}
		if(f instanceof Movable){
			System.out.println("f는 Moveable클래스의 자손입니다.");
		}
		if(f instanceof Attackable){
			System.out.println("f Attackable는클래스의 자손입니다");
		}
		if(f instanceof Object){
			System.out.println("f는 Object클래스의 자손입니다.");
		}
		f.move(x,y);
		f.attack(u);
	}
}
//Fighter클래스의 구현부입니다. 
//class를 선언해주세요. 이름은 Fighter, Unit클래스 상속, Fightable 구현.
class Fighter extends Unit implements Fightable{
public void move(int x, int y){
	x =10;
	y = 10;
	System.out.println("x="+x+" y="+y);
	System.out.println("Movable인터페이스의 move메소드 구현 성공");
	
}
public void attack(Unit u){
	currentHP=50;
	x=10;
	y=10;
	System.out.println("HP:"+currentHP+" x="+x+" y="+y);
	System.out.println("Attackable 인터페이스의 attack메소드 구현");
	
}
}

class Unit{
	int currentHP;
	int x;
	int y;
	
}

interface Fightable extends Movable, Attackable{}
interface Movable{void move(int x, int y);}
interface Attackable{void attack(Unit uu); }
/*
리턴타입이 없는 move method를 안에넣으라고....?
*/


//인터페이스 도대체 왜 쓰는건지?? 인터페이스 부분이 없다고해도 똑같이 돌아가는건데 ~ 

