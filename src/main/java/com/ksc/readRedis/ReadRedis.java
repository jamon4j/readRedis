package com.ksc.readRedis;

import java.io.File;

import com.ksc.readRedis.io.FileOperation;

import redis.clients.jedis.Jedis;
/*
 * author:zjj
 * func:redis读取总控制类，根据key的不同类型调用不同拉取程序
 * para:args[0]为监听的key
 * date:2015/1/16 13:50
 */
public class ReadRedis {
	// redis服务器主机
    static String host = "127.0.0.1";
	//static String host = "10.0.3.239";
    // 端口号
    static int port = 6379;
	public static void main(String[] args) {
		ReadRedisString rrs = new ReadRedisString();
		ReadRedisList rrl = new ReadRedisList();
		
		Jedis jedis = new Jedis(host,port);  
		rrs.readRedisString(jedis,args[0]);
		/*if(jedis.type(args[0]).equals("string"))
		{
			rrs.readRedisString(jedis,args[0]);
		}
		if(jedis.type(args[0]).equals("list"))
		{
			rrl.readRedisList(jedis,args[0]);
		}*/
	}
}
