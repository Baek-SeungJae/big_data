package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSCopyTest {

	public static void main(String[] args) {
		Configuration conf = new Configuration();
		FileSystem hdfs = null;
		FSDataOutputStream hdfsout = null;
		FSDataInputStream hdfsin = null;
		try {
			hdfs=FileSystem.get(conf);
			Path inpath = new Path(args[0]);
			hdfsin = hdfs.open(inpath);
			Path outpath = new Path(args[1]);
			hdfsout = hdfs.create(outpath);
			
			hdfsout.writeUTF(hdfsin.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
