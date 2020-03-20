package pattern.exam;

import java.util.regex.Pattern;

public class EmailCheck {
	public static void main(String[] args) {
		//x{n}=>x를 n번 반복한 문자를 찾는다는 의미로 해석
		String suffix = "(\\.net|\\.co\\.kr|\\.com)";
		String emailReg="[a-zA-z0-9\\.]+@[a-zA-z0-9]+"+suffix;
		String[] user = {"heaves@hanmail,net","heaves@hanmail.net",
					"테스트heaves@hanmail.net","sc.com@hanmail.net",
					",heaves@hanmail.net","@hanmail.net"
					,"heaves@hanmail.co.kr"};
		//user에 입력된 email의 형식이 맞는지 true|false로 출력할 수 있도록 작업	
		// F T F T F F T
		int size = user.length;
		for(int i=0; i<size; i++) {
			System.out.println(Pattern.matches(emailReg, user[i]));
		}
	}
}