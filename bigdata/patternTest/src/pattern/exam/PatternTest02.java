package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest02 {

	public static void main(String[] args) {
		//String str = "jaaava programmainggm";
		String str = "ja1111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		//String patternStr = "a|m|g";			// 1. 는 or을 의미
		//String patternStr = "[amg]";			// 2. 1번과 동일
		//String patternStr = "[amg][ma]";		// 3. [][]사이에 들어간 조합으로 만들 수 있는 것들 2글자
		//String patternStr = "[c-j]";			// 4. c~j 사이에 있는 글자
		//String patternStr = "[C-J]";			// 마찬가지
		//String patternStr = "[C-jc-j]";		// 5. 대문자A-B , 소문자 c-j
		//String patternStr = "[4-8]";			// 6. 숫자도 가능
		//String patternStr = "[^4-8]";			// 7. ^가 대괄호 안에 있으면 부정의 의미
		//String patternStr = "[^c-j]";			// c-j를 제외
		//String patternStr = "[a-zA-Z0-9]";	// 영문자 숫자
		String patternStr = "[^a-zA-Z0-9 ]+";		// 영문자 숫자 제외
		//String patternStr = "[가-힣]";
		equalsPattern(str, patternStr);
	}
	
	public static void equalsPattern(String str, String patternStr) {
		// 1. 패턴을 인식
		//Pattern pattern = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
		Pattern pattern = Pattern.compile(patternStr);
		Matcher m = pattern.matcher(str);
		/*
		System.out.println(m.find());
		System.out.println(m.start());
		System.out.println(m.end());
		System.out.println(m.group());
		*/
		while(m.find()) {
			System.out.println(m.group());
			System.out.println(m.start()+":"+(m.end()-1));
		}
	}
}
