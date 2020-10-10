package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DistributedQueueTest
{
    @Test
    public void test() throws IOException {

        //运行前提：先启动 192.168.25.133服务器的 zookeeper ./zkServer.sh start

        DistributedQueue queue = new DistributedQueue();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new QueueProductor(queue));
        executorService.submit(new QueueConsumer(queue));

        System.in.read();//不让程序退出

        //测试结果：
        /**
         * 入队列，值: /distributedQueue/item0000000000，当前生产总数：1
         * 入队列，值: /distributedQueue/item0000000001，当前生产总数：2
         * 入队列，值: /distributedQueue/item0000000002，当前生产总数：3
         * 出队列，值: item0000000000，当前消费个数：1
         * 入队列，值: /distributedQueue/item0000000003，当前生产总数：4
         * 入队列，值: /distributedQueue/item0000000004，当前生产总数：5
         * 入队列，值: /distributedQueue/item0000000005，当前生产总数：6
         * 出队列，值: item0000000001，当前消费个数：2
         * 入队列，值: /distributedQueue/item0000000006，当前生产总数：7
         * 入队列，值: /distributedQueue/item0000000007，当前生产总数：8
         * 入队列，值: /distributedQueue/item0000000008，当前生产总数：9
         * 出队列，值: item0000000002，当前消费个数：3
         * 入队列，值: /distributedQueue/item0000000009，当前生产总数：10
         * 出队列，值: item0000000003，当前消费个数：4
         * 出队列，值: item0000000004，当前消费个数：5
         * 出队列，值: item0000000005，当前消费个数：6
         * 出队列，值: item0000000006，当前消费个数：7
         * 出队列，值: item0000000007，当前消费个数：8
         * 出队列，值: item0000000008，当前消费个数：9
         * 出队列，值: item0000000009，当前消费个数：10
         */
    }
}
