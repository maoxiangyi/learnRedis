package redis;

import redis.clients.jedis.Jedis;


public class RedisMain {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("storm01",6379);

        String response = jedis.ping();

        System.out.println(response);
    }
}
