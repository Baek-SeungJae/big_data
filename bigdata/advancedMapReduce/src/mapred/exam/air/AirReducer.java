package mapred.exam.air;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
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
		String[] data = key.toString().split(",");
		resultKey.set(data[1]);
		
		if (data[0].substring(0, 3).equals("198")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiOut.write("1980", resultKey, resultVal);
		}
		if (data[0].substring(0, 3).equals("199")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiOut.write("1990", resultKey, resultVal);
		}
		if (data[0].substring(0, 3).equals("200")) {
			int sum = 0;
			for (IntWritable value : values) {
				sum = sum + value.get();
			}
			resultVal.set(sum);
			multiOut.write("2000", resultKey, resultVal);
		}
	}
}