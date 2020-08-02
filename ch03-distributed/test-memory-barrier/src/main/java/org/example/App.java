package org.example;

/**
 * Hello world!
 *
 */
public class App
{


    public static void main( String[] args )
    {
        final ExeCpu exeCpu = new ExeCpu();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                exeCpu.executeCpu1();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            public void run() {
                exeCpu.executeCpu0();
            }
        });

        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
