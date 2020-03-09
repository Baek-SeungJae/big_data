package mapred.exam.air;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line[] = value.toString().split(",");
		if (line[1].length() == 1)
			line[1] = "0" + line[1];
		try {
			if (Integer.parseInt(line[15]) > 0) {
				outputKey.set(line[0]+","+line[0] + "." + line[1]);
				context.write(outputKey, outputVal);
			}
		} catch (NumberFormatException e) {
		}
	}
}