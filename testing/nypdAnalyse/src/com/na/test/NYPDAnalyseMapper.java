package com.na.test;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class NYPDAnalyseMapper extends Mapper <LongWritable, Text, Text , IntWritable>
{
	private Text dated = new Text();
	private IntWritable total = new IntWritable();
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		String token=value.toString();
		String[] tokens=token.split(",");
		int temp=0;
		tokens[0].toString();
		if(!tokens[0].equals("DATE")) {
			temp=Integer.parseInt(tokens[3])+Integer.parseInt(tokens[4]);
			dated.set(tokens[0]);
			total.set(temp);
			context.write(dated,total);	
			}
	
	}
		
}