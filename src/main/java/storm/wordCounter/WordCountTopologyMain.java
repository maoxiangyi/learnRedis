package storm.wordCounter;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

/**
 * 功能说明：
 * 设计一个topology，来实现对一个句子里面的单词出现的频率进行统计。
 * 整个topology分为三个部分：
 *      RandomSentenceSpout：数据源，在已知的英文句子中，随机发送一条句子出去。
 *      SplitSentenceBolt:负责将单行文本记录（句子）切分成单词
 *      WordCountBolt:负责对单词的频率进行累加
 */
public class WordCountTopologyMain {
	public static void main(String[] args) throws Exception {
		
		// Storm框架支持多语言，在JAVA环境下创建一个拓扑，需要使用TopologyBuilder进行构建
		TopologyBuilder builder = new TopologyBuilder();
		
		//RandomSentenceSpout类，在已知的英文句子中，随机发送一条句子出去。
		builder.setSpout("spout", new RandomSentenceSpout(), 1);
		
		// SplitSentenceBolt类，主要是将一行一行的文本内容切割成单词
		builder.setBolt("split", new SplitSentenceBolt(), 2).shuffleGrouping("spout");
		
		//WordCountBolt类，负责对单词的频率进行累加
		builder.setBolt("count", new WordCountBolt(), 12).fieldsGrouping("split",new Fields("word"));
		
		//启动topology的配置信息
		Config conf = new Config();
		//TOPOLOGY_DEBUG(setDebug), 当它被设置成true的话， storm会记录下每个组件所发射的每条消息。
		//这在本地环境调试topology很有用， 但是在线上这么做的话会影响性能的。
		conf.setDebug(false);
		
		//storm的运行有两种模式: 本地模式和分布式模式.
		if (args != null && args.length > 0) {
			//定义你希望集群分配多少个工作进程给你来执行这个topology
			conf.setNumWorkers(3);
			
			//向集群提交topology
			StormSubmitter.submitTopologyWithProgressBar(args[0], conf,
					builder.createTopology());
		} else {
			conf.setMaxTaskParallelism(3);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("word-count", conf, builder.createTopology());
//			Utils.sleep(10000);
//			cluster.shutdown();
		}
	}
}
