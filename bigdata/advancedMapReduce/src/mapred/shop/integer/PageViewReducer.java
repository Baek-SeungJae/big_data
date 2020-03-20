package mapred.shop.integer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer extends Reducer<MyKey, IntWritable, MyKey, IntWritable> {
	IntWritable resultVal = new IntWritable();
	MyKey resultKey = new MyKey();
	@Override
	protected void reduce(MyKey key, Iterable<IntWritable> values,
			Reducer<MyKey, IntWritable, MyKey, IntWritable>.Context context)
			throws java.io.IOException, InterruptedException {
		int sumuser = 0;
		int sumhit = 0;
		for (IntWritable value : values) {
			sumuser++;
			sumhit = sumhit + value.get();
		}
			resultKey.setProductID(key.getProductID());
			resultKey.setUserID(Integer.toString(sumuser));
			resultVal.set(sumhit);
			context.write(resultKey, resultVal);

	}
}