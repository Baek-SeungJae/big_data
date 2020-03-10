package mapred.basic;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//Reducer 
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	IntWritable resultVal = new IntWritable();
	Text appendData = new Text();
	String data = "";
	Text outputKey = new Text();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws java.io.IOException ,InterruptedException {
		int sum = 0;
		data = data + "reduce호출";
		appendData.set(data);
		for(IntWritable value : values) {
			sum = sum+ value.get();
		}
		resultVal.set(sum);
		outputKey.set(key+":"+appendData);
		context.write(outputKey, resultVal);
	}
}
