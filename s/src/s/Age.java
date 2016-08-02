package s;

import java.util.Scanner;

class AgeInputException extends Exception {

	AgeInputException() {
		super("유효하지 않는 나이가 입력되었습니다.");
	}
}

class NameLengthException extends Exception {
	String wrongName;

	NameLengthException(String s) {
		super("잘못된 이름이 삽입되었씁니다. ");
		wrongName = s;
	}

	public void showWrongName() {
		System.out.println("wrong name : " + wrongName);

	}
}

class PersonalInfo {
	String name;
	int age;

	public PersonalInfo(String name, int age) {
		this.name = name;
		this.age = age;

	}

	public void showPersoalInfo() {
		System.out.println("NAME: " + name);
		System.out.println("AGE: " + age);

	}
}

public class Age {
	public static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) throws AgeInputException {

	}

	public static PersonalInfo readPersonalInfo() throws AgeInputException, NameLengthException {
		String name = readName();
		int age = readAge();
		PersonalInfo pInfo = new PersonalInfo(name, age);
		return pInfo;
	}

	public static String readName() throws NameLengthException {
		System.out.println("Name iput: ");
		String name = keyboard.nextLine();

		if (name.length() < 2) {
				throw new NameLengthException(name);
		}
	}

	return name;

	}

public static int readAge(){
	System.out.println("AGE INPUT: ");
	int age = keyboard.nextInt();
	
	return age;
}
