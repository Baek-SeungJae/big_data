package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest05 {

	public static void main(String[] args) {
		//String str = "tomato jaava tomato prog potato";
		//String patternStr = "(tom|pot)ato";
		
		//String str = "aaaaa aaabc iiiii iiibc aiabc";
		//String patternStr = "(a|i){3}bc";
		String str = "사람은 0012이 asd123가";
		//String patternStr = "([a-z][0-9])";
		String patternStr = "[a-z0-9가-힣]+[^은는이가 ]";
		equalsPattern(str, patternStr);
	}
	
	public static void equalsPattern(String str, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher m = pattern.matcher(str);
		while(m.find()) {
			System.out.println(m.group());
			System.out.println(m.start()+":"+(m.end()-1));
		}
	}
}
