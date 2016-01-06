package redis.string;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Describe: 案列，计算舞林大会多个擂台的比武场次
 * 主要知识点：线程池、String.incr() String.get()等
 * Author:   maoxiangyi
 * Domain:   www.maoxiangyi.cn
 * Data:     2016/1/6.
 */
public class Counter {
    /**
     * 计算武林大会三个擂台的比武次数
     *
     * @param args
     */
    public static void main(String[] args) {
        //创建一个固定大小的线程池，3个擂台
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //擂台：天龙八部
        executorService.submit(new Arena("biwu:totalNum","天龙八部"));
        //擂台：神雕侠侣
        executorService.submit(new Arena("biwu:totalNum","神雕侠侣"));
        //擂台：倚天屠龙记
        executorService.submit(new Arena("biwu:totalNum","倚天屠龙记"));
        //报幕人员，一秒统计一次总共比了多少场
        executorService.submit(new BaoMu("biwu:totalNum"));
    }
}
