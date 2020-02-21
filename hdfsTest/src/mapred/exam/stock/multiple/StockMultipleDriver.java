package mapred.exam.stock.multiple;
// 한 개의 입력 데이터를 이용해서 여러 개의 output을 만들고 싶은 경우 사용
// Mapper : GenericOptionParser 작업할 때와 동일하게 Map메소드를 구성하며 보통 구분할 수 있도록 각 상황별로 key에 문자열만 추가해준다.
// Reducer : mapper에서 넘겨준 데이터에서 구분자를 기준으로 분리해서 합산 - 개별 output이 생성될 수 있도록 처리
//	- setup : Reducer객체가 처음 실행될 때 한 번만 호출 되는 메소드 MultipleOutputs객체를 생성
//	- reducer : 각각의 상황별로 write가 호출될 수 있도록 처리 (여기서는 up, down, equal)
//	- cleanUp : Reducer의 작업이 종료될 때 한 번만 호출되는 메소드 MultipleOutputs객체를 해제(반드시 처리)
// Driver : MultipleOutputs로 출력될 경로를 Path에 설정 prefix로 구분문자열을 정의
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class StockMultipleDriver extends Configured implements Tool{
	@Override
	public int run(String[] optionlist) throws Exception {
		GenericOptionsParser parser = new GenericOptionsParser(getConf(), optionlist);
		String[] otherArgs = parser.getRemainingArgs();
		Job job = new Job(getConf(), "Stock_multi");
		
		job.setMapperClass(StockMultipleMapper.class);
		job.setReducerClass(StockMultipleReducer.class);
		job.setJarByClass(StockMultipleDriver.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		MultipleOutputs.addNamedOutput(job, "up", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "down", TextOutputFormat.class, Text.class, IntWritable.class);
		MultipleOutputs.addNamedOutput(job, "eq", TextOutputFormat.class, Text.class, IntWritable.class);
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new StockMultipleDriver(), args);
	}
}
