package mapred.exam01;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapperExam extends Mapper<LongWritable, Text, Text, IntWritable> {
	// output 데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수
	// output 데이터의 value는 무조건 1이므로 상수로 정의
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// key는 linenumber ex) 1
		// value는 입력데이터의 한 라인에 해당하는 문장 ex) read a book
		StringTokenizer st = new StringTokenizer(value.toString());
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			outputKey.set(token);// output데이터의 키를 세팅
			// context객체의 write메소드를 통해서 output으로 내보낼 데이터의 key와 value를 정의
			if (outputKey.toString().length() >= 5) {
				context.write(outputKey, outputVal);
			}
		}
	}
}