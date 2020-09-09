package org.example;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于zookeeper实现的一个分布式队列。
 * 这个demo 有3个角色：队列、消费者、生产者。会演示多线程分别生产、消费10个消息
 */
public class DistributedQueue {

    CuratorFramework curatorFramework = null;

    public DistributedQueue() {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.25.133:2181")
                .sessionTimeoutMs(5 * 1000)//心跳间隔
                .connectionTimeoutMs(3 * 1000)//连结超时
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))//重试策略：衰减重试。（第一次每隔1s去重试，后面越来越长的时间）
                .build();
        curatorFramework.start();

        //初始化跟节点（文件夹）
        try {
            Stat stat = curatorFramework.checkExists().forPath(rootPath);
            if(stat != null){
                curatorFramework.delete().forPath(rootPath);//删除
            }

            curatorFramework.create()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(rootPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String rootPath = "/distributedQueue";

    public String push() throws Exception {
        String path = curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL)//有序持久化节点
                .forPath(rootPath + "/item");//创建子节点，eg: /distributedQueue/item0000000001
        //System.out.println("创建的path: " + path);
        return path;
    }

    //检索
    public List<String> getAll() throws Exception {
        List<String> children = curatorFramework.getChildren().forPath(rootPath);
        Collections.sort(children);
        return children;
    }

    public String pop() throws Exception {

        List<String> children = getAll();

        //System.out.println("当前节点列表：");
        //children.forEach(System.out::println);

        String first = null;
        if (children.size() > 0) {
            first = children.get(0);
            curatorFramework.delete().forPath(rootPath + "/" + first);
        }

        children = getAll();
        //System.out.println("删除后的节点列表：");
        //children.forEach(System.out::println);

        return first;
    }

    public static void main(String[] args) throws Exception {

        DistributedQueue queue = new DistributedQueue();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new QueueProductor(queue));
        executorService.submit(new QueueConsumer(queue));

        System.in.read();//不让程序退出
    }
}

class QueueProductor implements Runnable {

    DistributedQueue queue;

    public QueueProductor(DistributedQueue queue) {
        this.queue = queue;
    }

    AtomicInteger productCount = new AtomicInteger();

    @Override
    public void run() {

        try {
            while (true) {

                synchronized (productCount) {
                    String push = queue.push();
                    if (push == null && push.equals("")) {
                        System.out.println("入队列失败");
                        return;
                    }

                    int currentCount = productCount.addAndGet(1);
                    System.out.println("入队列，值: " + push + "，当前生产总数：" + currentCount);

                    if (currentCount >= 10) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class QueueConsumer implements Runnable {

    DistributedQueue queue;

    public QueueConsumer(DistributedQueue queue) {
        this.queue = queue;
    }

    AtomicInteger consumeCount = new AtomicInteger();

    @Override
    public void run() {

        try {
            while (true) {

                synchronized (consumeCount) {

                    String push = queue.pop();
                    if (push == null && push.equals("")) {
                        System.out.println("出队列失败");
                        return;
                    }

                    int currentCount = consumeCount.addAndGet(1);
                    System.out.println("出队列，值: " + push + "，当前消费个数：" + currentCount);
                    if (currentCount >= 10) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}