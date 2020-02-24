package pattern.exam;

import java.util.regex.Pattern;

//8글자이상, 대문자, 소문자, 특수문자, 숫자가 모두 포함

public class PasswordCheck {

	public static boolean isUC(String str) {
		String UC = ".*[A-Z].*";
		String ipreg = UC;
		return Pattern.matches(ipreg, str);
	}
	public static boolean isLC(String str) {
		String LC = ".*[a-z].*";
		String ipreg = LC;
		return Pattern.matches(ipreg, str);
	}
	public static boolean isNUM(String str) {
		String NUM = ".*[0-9].*";
		String ipreg = NUM;
		return Pattern.matches(ipreg, str);
	}
	public static boolean isSC(String str) {
		String SC = ".*[^a-zA-Z0-9 ].*";
		String ipreg = SC;
		return Pattern.matches(ipreg, str);
	}
	public static boolean length8(String str) {
		String len = ".{8,}";
		String ipreg = len;
		return Pattern.matches(ipreg, str);
	}
	

	public static void main(String[] args) {
		String pass1 = "ABcd12!@"; //true
		System.out.println(isUC(pass1)&&isLC(pass1)&&isNUM(pass1)&&isSC(pass1)&&length8(pass1));
		String pass2 = "ABcd1212"; //false
		System.out.println(isUC(pass2)&&isLC(pass2)&&isNUM(pass2)&&isSC(pass2)&&length8(pass2));
		String pass3 = "ab!@12cd"; // false
		System.out.println(isUC(pass3)&&isLC(pass3)&&isNUM(pass3)&&isSC(pass3)&&length8(pass3));
		String pass4 = "A1a!B2b@"; // true
		System.out.println(isUC(pass4)&&isLC(pass4)&&isNUM(pass4)&&isSC(pass4)&&length8(pass4));
		String pass5 = "abcdefg"; // false
		System.out.println(isUC(pass5)&&isLC(pass5)&&isNUM(pass5)&&isSC(pass5)&&length8(pass5));
		String pass6 = "A!1a"; // false
		System.out.println(isUC(pass6)&&isLC(pass6)&&isNUM(pass6)&&isSC(pass6)&&length8(pass6));
	}
}
