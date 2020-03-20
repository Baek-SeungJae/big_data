package mapred.shop.integer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;


public class MyKeyPartitioner extends Partitioner<MyKey, IntWritable> {

	// numPartitions 파티션의 개수 = 리듀스태스크의 개수
	@Override
	public int getPartition(MyKey key, IntWritable value, int numPartitions) {
		int hash = key.getProductID().hashCode();
		int partition = hash % numPartitions;
		return partition;
	}
}