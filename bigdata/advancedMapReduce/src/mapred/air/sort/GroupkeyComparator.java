package mapred.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupkeyComparator extends WritableComparator{
	public GroupkeyComparator() {
		super(CustomKey.class,true);
	}
	
	//WritableComparable의 타입이 불분명하기 때문에 발생하는 warning을 무시
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		CustomKey k1 = (CustomKey) obj1;
		CustomKey k2 = (CustomKey) obj2;
		return k1.getYear().compareTo(k2.getYear());
	}
}
