package mr.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StartsWithCountMapper_UserCounters extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable countOne = new IntWritable(1);
	private final Text reusableText = new Text();
	
	enum Tokens {
		Total, FirstCharUpper, FirstCharLower
	}
	
	@Override	
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer tokenizer = new StringTokenizer(value.toString());
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			reusableText.set(token.substring(0, 1));			
			context.write(reusableText, countOne);
			
			context.getCounter(Tokens.Total).increment(1);
			char firstChar = token.charAt(0);
			if ( Character.isUpperCase(firstChar)){
				context.getCounter(Tokens.FirstCharUpper).increment(1);
			} else {
				context.getCounter(Tokens.FirstCharLower).increment(1);
			}
		}
	}
	
}
