package storm.wordCounter;

/**
 * Describe: 请补充类描述
 * Author:   maoxiangyi
 * Domain:   www.itcast.cn
 * Data:     2015/12/10.
 */
public class TestMain {

    public static void main(String[] args) {
        Person person = new Person();
        person.open();
        while (true) {
            person.execute("我爱我的祖国！");
        }
    }
}
