package mapred.exam.stock.multiple;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line[] = value.toString().split(",");
		try {
			if (Double.parseDouble(line[6]) > Double.parseDouble(line[3])) {
				outputKey.set("up,"+line[2].substring(0, 4));
				context.write(outputKey, outputVal);
			} else if (Double.parseDouble(line[6]) < Double.parseDouble(line[3])) {
				outputKey.set("down,"+line[2].substring(0, 4));
				context.write(outputKey, outputVal);
			} else{
				outputKey.set("eq,"+line[2].substring(0, 4));
				context.write(outputKey, outputVal);
			}
		} catch (NumberFormatException e) {
		}
	}
}
