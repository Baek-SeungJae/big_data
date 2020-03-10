package mapred.shop.integer;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupkeyComparator extends WritableComparator {
	public GroupkeyComparator() {
		super(MyKey.class, true);
	}

	// WritableComparable의 타입이 불분명하기 때문에 발생하는 warning을 무시
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		MyKey k1 = (MyKey) obj1;
		MyKey k2 = (MyKey) obj2;
		return k1.getProductID().compareTo(k2.getProductID());
	}
}
