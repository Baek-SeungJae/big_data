package mapred.air.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * 사용자가 -D 옵션을 이용해서 입력한 옵션 값을 프로그램 안에서 사용하기 위해서
 * 즉, Mapper가 사용할 수 있도록 전달 
 * 1. Configured와 Tool을 상속해야 한다.
 * 		=> Configured는 환경설정 정보를 활용
 * 		=> Tool은 사용자정의 옵션을 사용하기 위해
 * 2. run메소드를 오버라이딩
 * 		=> run메소드 안에 Driver에서 구현했던 모든 코드를 구현
 * 3. run메소드를 main메소드에서 실행되도록 호출해야 한다.
 *		=> run메소드를 직접 호출할 수 없고 ToolRunner라는 헬퍼클래스를 이용해서 호출
 */
public class AirCombinerDriver extends Configured implements Tool {
	@Override
	public int run(String[] optionlist) throws Exception {
		// 런 메소드는 사용자가 입력한 모든 옵션에 대한 정보를 String[]로 전달받는다.
		// -D를 입력하고 설정한 옵션과 사용자가 입력한 명령행 매개변수를 분리하여 관리해야 한다.
		// getRemainingArgs()를 이용해서 공통옵션(-D와 입력한 값 이외의 것들)과 상용자가 입력한 옵션을 따로 분리한다.
		GenericOptionsParser parser = new GenericOptionsParser(getConf(), optionlist);
		String[] otherArgs = parser.getRemainingArgs();

		// 1. 맵리듀스를 처리하기 위한 job 생성
		/* Configuration conf = new Configuration(); */
		Job job = new Job(getConf(), "AirCombiner");

		// 2. 실제 job을 처리하기 위한 클래스가 어떤 클래스인지 등록
		// 실제 우리가 구현한 Mapper, Reducer, Driver를 등록
		job.setMapperClass(AirCombinerMapper.class);
		job.setReducerClass(AirCombinerReducer.class);
		job.setJarByClass(AirCombinerDriver.class);
		job.setCombinerClass(AirCombinerReducer.class);

		// 3. HDFS에서 읽고 쓸 input데이터와 output데이터의 포맷을 정의
		// => hdfs에 텍스트파일의 형태로 input/output을 처리
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		// 4. 리듀스의 출력데이터에 대한 key와 value의 타입을 정의
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// 5. HDFS에 저장된 파일을 읽고 쓸 수 있도록 Path객체를 정의
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		// 6. 1~5번까지 설정한 내용을 기반으로 job이 실행되도록 명령
		job.waitForCompletion(true);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new AirCombinerDriver(), args);
	}
}
