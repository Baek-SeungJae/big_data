package mapred.shop.integer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

// 복합키를 정의 - 사용자정의 키(정렬할 기준을 컬럼으로 갖고 있는 객체)
// 맵리듀스 프레임워크 내부에서 키와 value는 네트워크에서 주고받는 값이므로 네트워크 전송을 하기 위해 제공되는 Writeable타입이어야 하므로 
// WriteableComparable을 상속받아 작성한다.
public class MyKey implements WritableComparable<MyKey> {
	private String productID;
	private String userID;

	public MyKey() {}

	public MyKey(String productID, String userID) {
		this.productID = productID;
		this.userID = userID;
	}

	@Override
	public String toString() {
		return productID + "\t" + userID+"\t";
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		productID = WritableUtils.readString(in);
		userID = WritableUtils.readString(in);
	}
	@Override
	public void write(DataOutput out) throws IOException {
		WritableUtils.writeString(out, productID);
		WritableUtils.writeString(out, userID);
		
	}
	@Override
	public int compareTo(MyKey obj) {
		int result = productID.compareTo(obj.productID);
		if(0==result) {
			result = userID.compareTo(obj.userID);
		}
		return result;
	}
}
