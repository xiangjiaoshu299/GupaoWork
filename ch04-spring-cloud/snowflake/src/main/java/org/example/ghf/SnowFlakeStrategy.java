package org.example.ghf;


import lombok.extern.slf4j.Slf4j;
import sun.nio.cs.ext.MacHebrew;

/**
 * 雪花算法策略
 */
@Slf4j
public class SnowFlakeStrategy {


    /**
     * 生成id的格式： 64个bit
     * 42位时间戳 + 机房id 5位  + 机器id 5位  +  sequemce 12位
     */

    //private int TIME_BIT_LEN = 42;//时间戳？位
    private int ROOM_BIT_LEN = 5;//机房id占？位
    private int MACHINE_BIT_LEN = 5;
    private int SEQU_BIT_LEN = 12;


    private long maxRoomId = getMaxNum(ROOM_BIT_LEN);//最大机房id
    private long maxMachineId = getMaxNum(MACHINE_BIT_LEN);
    private long maxSequId = getMaxNum(SEQU_BIT_LEN);

    //机器id，应该左移的位数
    int machineIdShift = SEQU_BIT_LEN;
    //机房id，应该左移的位数
    int roomIdShift = MACHINE_BIT_LEN + SEQU_BIT_LEN;
    //时间id，应该左移的位数
    int timeIdShift = ROOM_BIT_LEN + MACHINE_BIT_LEN + SEQU_BIT_LEN;

    /**
     * 最后一次的时间戳。
     * 作用：用来判断是否是同一毫秒内再次生成id
     */
    private long lastTimestamp;

    //初始化的时间，用来计算时间id时，充当减数。注意：这个时间不一样，生成id的位数也不一样
    private long firstTimestamp = 1597217755790L;

    //用户决定的id，保存下来
    private long roomId;
    private long machineId;
    /**
     * 第一个序列号。
     * 同一机房、同一机器、同一毫秒内，如果再次生成id，则靠这个序列号来保持id的互斥
     */
    private long sequId;

    public SnowFlakeStrategy(long roomId, long machineId, long firstSequId) {

        if (roomId > maxRoomId || roomId < 0) {
            log.error("机房id错误");
            return;
        }

        if (machineId > maxMachineId || roomId < 0) {
            log.error("机器id错误");
            return;
        }

        if (firstSequId < 0) {
            log.error("序列号错误");
            return;
        }

        this.roomId = roomId;
        this.machineId = machineId;
        this.sequId = firstSequId;

        //初始化时间戳
        lastTimestamp = System.currentTimeMillis();

        log.info("sequMask: {}, machineShift:{}, roomShift:{}, timeShift:{}"
                , maxSequId, machineIdShift, roomIdShift, timeIdShift);
    }

    public long generateId() {

        long timeMillis = System.currentTimeMillis();
        if (lastTimestamp == timeMillis) {
            sequId = sequId + 1;
            if (sequId >= maxSequId) {
                timeMillis = nextMillionsecond(timeMillis);
                sequId = 0;
                log.debug("sequId达到最大");
            } else {
                log.debug("sequId正常递增");
            }
        } else {
            log.debug("进入下一个毫秒，sequId重置0");
            sequId = 0;

            //如果每次获取id的时候，时间都不一样，则sequ始终都是0，会导致id始终都是偶数，所以这里处理一下
            sequId = timeMillis & 1;
        }

        log.debug("time:{}, sequId:{}", timeMillis, sequId);

        //提示：
        // 使用符号"<<"，可以让一个数对应的二进制数，左移。这里4个数字都左移不一样的位数，是不会冲突的
        // 使用符号"|"可以让几个二进制数，合并成1个二进制数。

        long res = ((timeMillis - firstTimestamp) << timeIdShift)
                | (roomId << roomIdShift)
                | (machineId << machineIdShift)
                | sequId;

        lastTimestamp = timeMillis;

        return res;
    }

    /**
     * 得到下一个毫秒
     *
     * @param timeMillis
     * @return
     */
    private long nextMillionsecond(long timeMillis) {
        long curr = System.currentTimeMillis();
        while (curr <= timeMillis) {
            curr = System.currentTimeMillis();
        }
        return curr;
    }

    /**
     * 得到？个二进制位时，对应的最大十进制数
     *
     * @param bitCount
     * @return
     */
    private static long getMaxNum(int bitCount) {
        //求？个bit，最大的数字，也就是每个bit上都是1的情况，对应的十进制数
        long maxOfXBit = -1L ^ (-1L << bitCount);
//        System.out.println(maxOfXBit);

//        //法2
//        long maxOfXBit = (1L << bitCount) - 1;
//        System.out.println(maxOfXBit);

        return maxOfXBit;
    }

    public static void main(String[] args) throws InterruptedException {
        //随便找个时间，充当firstTimestamp
        //System.out.println(System.currentTimeMillis());


        SnowFlakeStrategy snow = new SnowFlakeStrategy(1, 1, 1);
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1);
            long id = snow.generateId();
            log.info("id:{}", id);
        }
    }
}
