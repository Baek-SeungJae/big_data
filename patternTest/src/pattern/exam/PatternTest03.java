package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest03 {

	public static void main(String[] args) {
		//String str = "jaaava programmainggm";
		//String str = "-@-ja1- -111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		String str = "amJAVA _java aaaxl  programming and spring , hadoop";
		//String patternStr = "[amv]{1,3}";			// 1. a, m, v, aa, am, av, ma, mm, mv, va, vm, vv, aaa, 등...
		//String patternStr = "[a-z]{3,}";			// 2. a-z까지 3글자 이상인
		//String patternStr = "\\W";				// 3. 대문자 소문자 숫자 뺀 모두
		//String patternStr = "\\w";				// 4. 대문사 소문자 숫자 모두
		//String patternStr = "\\d";				// 5. 숫자만
		String patternStr = "\\D";				// 6. 숫자빼고 나머지
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
