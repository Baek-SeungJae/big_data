package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest05 {

	public static void main(String[] args) {
		//String str = "tomato jaava tomato prog potato";
		//String patternStr = "(tom|pot)ato";
		
		//String str = "aaaaa aaabc iiiii iiibc aiabc";
		//String patternStr = "(a|i){3}bc";
		String str = "사람은 0012이 asd123가 테스트이라고. 테, (asd)";
		//String patternStr = "([a-z][0-9])";
		String patternStr = "[a-z0-9가-힣]+[^은는이가의에을를 ,.:()'이다''에서''에게'께'한테''보다''로서''라고''이라고''처럼''만큼''하고''이며''있다''말한다''하다']";
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
