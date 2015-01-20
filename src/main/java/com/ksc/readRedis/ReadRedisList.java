package com.ksc.readRedis;

import java.io.File;

import redis.clients.jedis.Jedis;

import com.ksc.readRedis.io.FileOperation;
/*
 * author:zjj
 * func:监听redis,拉取（lpop）redis List内容并写入日志
 * para:args[0]为监听的key
 * date:2015/1/16 13:50
 */
public class ReadRedisList {
    public void readRedisList(Jedis jedis,String key)
    {
    	FileOperation fo = new FileOperation();
		File log_file = fo.getLogFile(key);//获取日志文件
		String result = "";
		while(true)
		{
			while(true)
			{
				result = "";
				long size = jedis.llen(key);
				if(size!=0)
				{
					for(int i=0;i<size;i++)
					{
						String value = jedis.lpop(key);
						result += value;
					}
					fo.writeLogFile(log_file, result);//将redis读拉出的内容写入日志文件
				}			
				break;
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
}
