package mapred.shop.text;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class MyKeyPartitioner extends Partitioner<MyKey, Text> {

	// numPartitions 파티션의 개수 = 리듀스태스크의 개수
	@Override
	public int getPartition(MyKey key, Text value, int numPartitions) {
		int hash = key.getProductID().hashCode();
		int partition = hash % numPartitions;
		return partition;
	}
}