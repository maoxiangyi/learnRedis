package redis;

import redis.clients.jedis.Jedis;


/**
 * Describe: 创建redis客户端，并测试连接是否正常
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class RedisMain {
    public static void main(String[] args) {
        //创建redis客户端
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //运行redis ping命令，正常返回pong
        String response = jedis.ping();
        //打印结果，正常结果为pong
        System.out.println(response);
    }
}
