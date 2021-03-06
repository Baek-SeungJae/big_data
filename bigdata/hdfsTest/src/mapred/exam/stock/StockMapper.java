package mapred.exam.stock;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	// output 데이터를 mapper의 실행결과로 내보낼 수 있도록 키와 value를 저장하는 변수
	// output 데이터의 value는 무조건 1이므로 상수로 정의
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// key는 linenumber ex) 1
		// value는 입력데이터의 한 라인에 해당하는 문장 ex) read a book
			StringTokenizer st = new StringTokenizer(value.toString(),",");	

			st.nextToken();
			st.nextToken();
			String date = st.nextToken();
			String stock_price_open = st.nextToken();
			st.nextToken();
			st.nextToken();
			String stock_price_close = st.nextToken();
			st.nextToken();
			st.nextToken();
			try {
				if(Double.parseDouble(stock_price_close)>Double.parseDouble(stock_price_open)) {
					outputKey.set(date.substring(0, 4));
					context.write(outputKey, outputVal);
				}
			} catch(NumberFormatException e) {
				
			}
	}
}
