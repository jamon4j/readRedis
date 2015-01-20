package com.ksc.readRedis.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * author:zjj
 * func:文件操作类
 * date:2015/1/14 14:00
 */
public class FileOperation {
	//DIR：存放日志文件的文件夹路径/data/projects/readRedis/log/
	private static final String DIR = File.separator+"data"+File.separator+"projects"+File.separator+"readRedis"+File.separator+"log"+File.separator;
	FileWriter fw = null;
	BufferedWriter bw = null;
	
	/*获取日志文件*/
	public File getLogFile(String keyName)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		String filename = keyName+"_"+time;//根据年月日创建的文件名，每日创建新日志文件
		
		File log_file = new File(DIR,filename);
		if(!log_file.exists())
		{
			try {
				log_file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件创建失败");
				e.printStackTrace();
			}
		}
		return log_file;
	}
	
	/*将字符串text写入文件file中*/
	public void writeLogFile(File file,String text)
	{		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String time = df.format(new Date());// new Date()为获取当前系统时间
		try {
			fw = new FileWriter(file,true);
			bw = new BufferedWriter(fw);
			bw.write(text); 
			bw.newLine();	//换行
			bw.write(time); //写入记录时间
			bw.newLine();   //换行
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) bw.close();
				if(fw != null) fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
