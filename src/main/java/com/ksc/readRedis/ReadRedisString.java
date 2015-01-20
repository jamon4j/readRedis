package com.ksc.readRedis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.ksc.readRedis.io.FileOperation;

import redis.clients.jedis.Jedis;
/*
 * author:zjj
 * func:监听redis,读取redis String内容并写入日志
 * para:args[0]为监听的key
 * date:2015/1/13 13:50
 */
public class ReadRedisString {
    public void readRedisString(Jedis jedis,String key)
    {
    	FileOperation fo = new FileOperation();		
		while(true)
		{
			while(true)
			{
				File log_file = fo.getLogFile(key);//获取日志文件
				/*如果key存在，则拉取数据后删除key*/
				if(jedis.exists(key))
				{
					String value = jedis.get(key);
					jedis.del(key);
					if(!value.equals(""))
					{
						fo.writeLogFile(log_file, value);//将redis读取出的内容写入日志文件
					}
				}		
				break;	
			}
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
} 
