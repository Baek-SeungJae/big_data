package mapred.air.combiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 하둡을 실행할 때 사용자가 입력하는 옵션을 Mapper 내부에서 사용할 수 있도록
// 옵션이 어떤 값으로 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
// 
public class AirCombinerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
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
				outputKey.set(line[0] + "." + line[1]);
				context.write(outputKey, outputVal);
			}
		} catch (NumberFormatException e) {
		}
	}
}