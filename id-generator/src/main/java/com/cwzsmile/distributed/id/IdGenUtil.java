package com.cwzsmile.distributed.id;

/**
 * @author mc
 * @date 2019-07-06.
 */
public class IdGenUtil {

    /**
     * SnowFlake所生成的ID一共分成四部分：
     1.第一位
     占用1bit，其值始终是0，没有实际作用。
     2.时间戳
     占用41bit，精确到毫秒，总共可以容纳约140年的时间。
     3.工作机器id
     占用10bit，其中高位5bit是数据中心ID（datacenterId），低位5bit是工作节点ID（workerId），做多可以容纳1024个节点。
     4.序列号
     占用12bit，这个值在同一毫秒同一节点上从0开始不断累加，最多可以累加到4095。SnowFlake算法在同一毫秒内最多可以生成多少个全局唯一ID呢？只需要做一个简单的乘法：
     同一毫秒的ID数量 = 1024 X 4096 = 4194304 这个数字在绝大多数并发场景下都是够用的。
     */
    public static void snowFlakeGen() {

    }
}
