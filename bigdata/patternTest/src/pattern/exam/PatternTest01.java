package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest01 {

	public static void main(String[] args) {
		//String str = "java, h1 ~~~ java";
		String str = "$100. .한 $20.0 ^^$";
		//String patternStr = "java";		// 1. 정확학 일치하는 것
		//String patternStr = "^java";		// 2. ^뒤의 문자열로 시작
		//String patternStr = "java$";		// 3. $앞의 문자열로 종료
		//String patternStr = "^\\$";		// 4. 패턴에서 사용하는 기호는 일반 문자열로 인식하지 않는다. 패턴에서 사용하는 기호를 문자열로 인식시키려면 \\를 같이 사용해야 함
		//String patternStr = "\\$$";		// 5. $로 끝나는 문자열인지 검색
		//String patternStr = ".";			// 6. .은 한 글자를 의미
		//String patternStr = "....";		// 7. 문자길이가 4
		//String patternStr = "\\.";		// 8. .이 포함된 문자열
		//String patternStr = "\\..\\.";	// 9. '. .'
		String patternStr = " ";
		equalsPattern(str, patternStr);
	}
	public static void equalsPattern(String str, String patternStr) {
		// 1. 패턴을 인식
		Pattern pattern = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE);
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