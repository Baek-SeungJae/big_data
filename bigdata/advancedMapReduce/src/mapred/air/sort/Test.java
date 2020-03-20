package mapred.air.sort;

public class Test {

	public static void main(String[] args) {
		String data1 = "f";
		String data2 = "a";
		System.out.println((int) data1.toCharArray()[0]);
		System.out.println((int) data2.toCharArray()[0]);

		// 문자열 비교 - compareTo
		// 아스키코드로 비교
		// 동일 = 0
		// -1 : 기준문자열이 매개변수보다 작다
		// 1 : 기준문자열이 매개변수보다 크다

		System.out.println(data1.compareTo(data2));

		// 해시코드란 객체를 구분할 수 있는 정수값
		// => 정수값은 객체가 할당된 주소를 가지고 만든다.
		// => hashcode메소드를 이용하면 이 정수값이 리턴된다.

		CustomKey key1 = new CustomKey();
		CustomKey key2 = new CustomKey();
		System.out.println(key1.hashCode());
		System.out.println(key2.hashCode());

		key1.setYear("1987");
		key2.setYear("1987");
		// 문자열에서 호출하는 해시코드는 Object의 Hashcode 메소드가 오버라이딩되어 주소를 가지고 정수값을 만들지 않고 문자열을 이용해서
		// 구분 정수값을 만들어서 리턴한다.
		// 따라서 문자열이 같으면 hashcode가 같다.
		System.out.println(key1.getYear().hashCode());
		System.out.println(key2.getYear().hashCode());
	}

}