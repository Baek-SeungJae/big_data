package mapred.exam.stock.option;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	String jobType;

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/*
		 * -D 옵션과 함께 사용자가 입력하는 jobType이라는 옵션의 지정한 값을 추출해서 mapper의 변수에 저장
		 */
		jobType = context.getConfiguration().get("jobType");
	}

	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line[] = value.toString().split(",");

		if (jobType.equals("EoR")) {
			try {
				if (Double.parseDouble(line[6]) > Double.parseDouble(line[3])) {
					outputKey.set(line[2].substring(0, 4));
					context.write(outputKey, outputVal);
				}
			} catch (NumberFormatException e) {}
		} else if (jobType.equals("EoD")) {
			try {
				if (Double.parseDouble(line[6]) < Double.parseDouble(line[3])) {
					outputKey.set(line[2].substring(0, 4));
					context.write(outputKey, outputVal);
				}
			} catch (NumberFormatException e) {}
		} else if (jobType.equals("EoE")) {
			try {
				if (Double.parseDouble(line[6]) == Double.parseDouble(line[3])) {
					outputKey.set(line[2].substring(0, 4));
					context.write(outputKey, outputVal);
				}
			} catch (NumberFormatException e) {}
		}

	}
}
