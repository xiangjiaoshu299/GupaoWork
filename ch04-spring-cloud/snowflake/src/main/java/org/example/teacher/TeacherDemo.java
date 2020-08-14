package org.example.teacher;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 */

public class TeacherDemo {


    public static void main(String[] args) throws InterruptedException {

        SnowFlakeGenerator snowFlakeGenerator = new SnowFlakeGenerator(1, 1, 1);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1);
            System.out.println(snowFlakeGenerator.nextVal());
        }
    }

}

