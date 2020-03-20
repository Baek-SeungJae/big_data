package mapred.basic;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//Reducer - 데이터를 집계하는 역할
/*
 *  1. Reducer
 *  2. reduce메소드를 오버라이딩
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	//reduce작업의 결과를 저장할 변수
	IntWritable resultVal = new IntWritable();
	// reduce 메소드의 매개변수
	// key => 입력데이터의 키타입
	// values => 입력데이터의 값의 타입 Iterable 즉, 입력값들이 Iterable형태로 전달 값은 IntWritable이지만 여러개가 전달되므로 반복작업을 수행해야 한다.
	// context => Mapper와 동일
	//			  맵리듀스 프레임워크 안에서 기본작업을 할 수 있도록 도와주는 역할
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws java.io.IOException ,InterruptedException {
		int sum = 0;
		for(IntWritable value : values) {
			sum = sum+ value.get();
		}
		resultVal.set(sum);
		context.write(key, resultVal);
	}
}
