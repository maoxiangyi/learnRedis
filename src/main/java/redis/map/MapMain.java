package redis.map;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Describe: 使用map保存用户信息
 *  主要知识点：del、hset、hgetAll、hkeys、hvals、hget、hincrBy、hdel、
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class MapMain {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.del("daxia:jingzhongyue");
        //创建一个对象
        jedis.hset("daxia:jingzhongyue", "姓名", "不为人知");
        jedis.hset("daxia:jingzhongyue", "年龄", "18");
        jedis.hset("daxia:jingzhongyue", "技能", "杀人于无形");

        //打印对象
        Map<String, String> jingzhongyue = jedis.hgetAll("daxia:jingzhongyue");
        System.out.println("大侠的基本信息：");
        for (Map.Entry entry : jingzhongyue.entrySet()) {
            System.out.println(entry.getKey() + "：-----------------" + entry.getValue());
        }
        System.out.println();

        //获取大侠的所有字段信息
        Set<String> fields = jedis.hkeys("daxia:jingzhongyue");
        for (String field : fields) {
            System.out.print(field + "  ");
        }
        System.out.println();
        //获取大侠的所有值的信息
        List<String> values = jedis.hvals("daxia:jingzhongyue");
        for (String value : values) {
            System.out.print(value + "  ");
        }
        System.out.println();

        //值获取大侠的年龄，进行研究
        String age = jedis.hget("daxia:jingzhongyue", "年龄");
        System.out.println("对大侠的年龄有质疑：" + age);
        //给大侠的年龄增加十岁
        jedis.hincrBy("daxia:jingzhongyue", "年龄", 10);
        System.out.println("经过验核，大侠的实际年龄为：" + jedis.hget("daxia:jingzhongyue", "年龄"));
        System.out.println();

        //删除大侠的姓名
        jedis.hdel("daxia:jingzhongyue", "姓名");
        for (Map.Entry entry : jedis.hgetAll("daxia:jingzhongyue").entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }
}
