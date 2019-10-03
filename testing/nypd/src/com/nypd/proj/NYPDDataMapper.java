package com.nypd.proj;
import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NYPDDataMapper extends Mapper <LongWritable, Text, NullWritable, Text>
{
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
	{
		String token=value.toString();
		String[] tokens=token.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);
		StringBuffer result = new StringBuffer();
		int temp=0;
		for(int i=10;i<=17;i++) {
			if(!tokens[i].isEmpty()) {
				temp+=1;	
			}
		}
		if(!tokens[0].isEmpty() && !tokens[2].isEmpty()&& !tokens[3].isEmpty() && temp == 8 && !tokens[24].isEmpty() && tokens.length==29) {
			result.append(tokens[0]).append(",");
			result.append(tokens[2]).append(",");
			result.append(tokens[3]).append(",");
			result.append(tokens[10]).append(",");
			result.append(tokens[11]).append(",");
			result.append(tokens[12]).append(",");
			result.append(tokens[13]).append(",");
			result.append(tokens[14]).append(",");
			result.append(tokens[15]).append(",");
			result.append(tokens[16]).append(",");
			result.append(tokens[17]).append(",");
			result.append(tokens[24]);
			context.write(NullWritable.get(),  new Text (result.toString()));	
	}
	}
		
}
