package com.na.test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class NYPDAnalyseReducer extends Reducer <Text, IntWritable, Text, IntWritable>
{
	private IntWritable result = new IntWritable();
	private Text finalDate = new Text();
	int temp=0;
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
	{
		int sum = 0;
		for (IntWritable val : values)
		{
			sum += val.get();
		}
		if(sum>temp)
		{
			temp=sum;
			finalDate.set(key);
		}
		result.set(temp);
	}
	@Override
	  protected void cleanup(Context context) throws IOException, InterruptedException {
	      context.write(finalDate,result);
	  }
}