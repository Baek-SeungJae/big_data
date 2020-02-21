package mapred.exam.stock.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class StockMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	IntWritable resultVal = new IntWritable();
	Text resultKey = new Text();
	MultipleOutputs<Text, IntWritable> multiOut;

	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut = new MultipleOutputs<Text, IntWritable>(context);
	}

	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		multiOut.close();
	}

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws java.io.IOException, InterruptedException {
		// 1. mapper에서 전달받은 key에서 구분값을 분리
		String[] data = key.toString().split(",");
		resultKey.set(data[1]);

		// 2. key에 추가된 구분자별로 output
		if (data[0].equals("up")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiOut.write("up", resultKey, resultVal);
		} else if (data[0].equals("down")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiOut.write("down", resultKey, resultVal);
		} else if (data[0].equals("eq")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiOut.write("eq", resultKey, resultVal);
		}

	}
}