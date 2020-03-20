package mapred.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CustomkeyComparator extends WritableComparator{
	public CustomkeyComparator() {
		super(CustomKey.class,true);
	}
	
	//WritableComparable의 타입이 불분명하기 때문에 발생하는 warning을 무시
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		CustomKey k1 = (CustomKey) obj1;
		CustomKey k2 = (CustomKey) obj2;
		
		int cmp = k1.getYear().compareTo(k2.getYear());
		if(cmp !=0) {
			return cmp;
		}
		else {
			return k1.getMonth().compareTo(k2.getMonth());
			
		}
	}
}