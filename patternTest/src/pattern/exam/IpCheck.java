package pattern.exam;


import java.util.regex.Pattern;

/*
 *  0~255까지 네자리
 *  250이상
 *  200-249
 *  100-199
 *  0-99
 */

public class IpCheck {
	public static boolean isIP(String str) {
		String addr = "([1-9]?[0-9]|[1][0-9][0-9]|[2][0-4][0-9]|[2][5][0-5])";
		String ipreg = addr+"[\\.]"+addr+"[\\.]"+addr+"[\\.]"+addr;
		return Pattern.matches(ipreg, str);
	}
	public static void main(String[] args) {
		System.out.println(isIP("127.0.0.1"));//true
    	System.out.println(isIP("196.168.59.101"));//true
    	System.out.println(isIP("250.0.8.9"));//true
    	System.out.println(isIP("196.168.59"));//false
    	System.out.println(isIP("300.168.59.101"));//false
    	System.out.println(isIP("300.168.592332.101"));//false
    	System.out.println(isIP("300.168.592.문자열"));//false
    	
    	
    	System.out.println(isIP("127.00.0.1")); //false
	}

}
