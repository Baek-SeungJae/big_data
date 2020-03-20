package mapred.exam.airoption;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 하둡을 실행할 때 사용자가 입력하는 옵션을 Mapper 내부에서 사용할 수 있도록
// 옵션이 어떤 값으로 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
// 
public class AirOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	String jobType; // 사용자가 입력하는 옵션 값을 저장하기 위한 변수

	// mapper가 실행될 때 한번만 실행되는 메소드
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

		if (jobType.equals("departure")) {
			// 출발지연
			try {
				if (Integer.parseInt(line[15]) > 0) {
					outputKey.set(line[1]);
					context.write(outputKey, outputVal);
				}
			} catch (NumberFormatException e) {
			}
		} else if (jobType.equals("arrival")) {
			// 출발지연
			try {
				if (Integer.parseInt(line[14]) > 0) {
					outputKey.set(line[1]);
					context.write(outputKey, outputVal);
				}
			} catch (NumberFormatException e) {
			}
		}
	}
}
