package mapred.shop.text;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer extends Reducer<MyKey, Text, Text, Text> {
	Text resultVal = new Text();
	Text resultKey = new Text();

	@Override
	protected void reduce(MyKey key, Iterable<Text> values, Reducer<MyKey, Text, Text, Text>.Context context)
			throws java.io.IOException, InterruptedException {
		int sumuser = 0;
		int sumhit = 0;
		String beforeUser = "";
		for (Text value : values) {
			String currentUser = value.toString();
			if (!beforeUser.equals(currentUser))
				sumuser++;
			sumhit++;
			beforeUser = currentUser;
		}
		
		resultKey.set(key.getProductID());
		StringBuffer data = new StringBuffer();
		data.append(sumuser).append("\t").append(sumhit);
		resultVal.set(data.toString());
		context.write(resultKey, resultVal);

	}
}