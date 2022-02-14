package concurrency.medium;

import java.util.concurrent.Semaphore;

/**
 * 交替打印 FooBar
 *
 * 给你一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例：
 *
 * 线程 A 将会调用 foo() 方法，而
 * 线程 B 将会调用 bar() 方法
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出："foobar"
 * 解释：这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出："foobarfoobar"
 * 解释："foobar" 将被输出两次。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 1000
 *
 * @author chengzhy
 * @date 2022/2/9 9:36
 */
public class P1115_PrintFooBarAlternately {

    public static class FooBar {
        private int n;
        // private Semaphore foo = new Semaphore(1);
        // private Semaphore bar = new Semaphore(0);
        /**
         * 1、wait()、notify/notifyAll() 方法是Object的本地final方法，无法被重写。
         * 2、wait()使当前线程阻塞，前提是 必须先获得锁，一般配合synchronized 关键字使用，
         * 即，一般在synchronized 同步代码块里使用 wait()、notify/notifyAll() 方法。
         * 3、 由于 wait()、notify/notifyAll() 在synchronized 代码块执行，说明当前线程一定是获取了锁的。
         * 当线程执行wait()方法时候，会释放当前的锁，然后让出CPU，进入等待状态。
         * 只有当 notify/notifyAll() 被执行时候，才会唤醒一个或多个正处于等待状态的线程，然后继续往下执行，直到执行完synchronized 代码块的代码或是中途遇到wait() ，再次释放锁。
         * 也就是说，notify/notifyAll() 的执行只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况。
         * 所以在编程中，尽量在使用了notify/notifyAll() 后立即退出临界区，以唤醒其他线程让其获得锁
         * 4、wait() 需要被try catch包围，以便发生异常中断也可以使wait等待的线程唤醒。
         * 5、notify 和wait 的顺序不能错，如果A线程先执行notify方法，B线程在执行wait方法，那么B线程是无法被唤醒的。
         * 6、notify 和 notifyAll的区别
         * notify方法只唤醒一个等待（对象的）线程并使该线程开始执行。所以如果有多个线程等待一个对象，这个方法只会唤醒其中一个线程，选择哪个线程取决于操作系统对多线程管理的实现。
         * notifyAll 会唤醒所有等待(对象的)线程，尽管哪一个线程将会第一个处理取决于操作系统的实现。如果当前情况下有多个线程需要被唤醒，推荐使用notifyAll 方法。比如在生产者-消费者里面的使用，每次都需要唤醒所有的消费者或是生产者，以判断程序是否可以继续往下执行。
         */
        private Object object = new Object();
        private volatile boolean isFoo = true;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                /*foo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                bar.release();*/
                synchronized (object) {
                    if (!isFoo) {
                        object.wait();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    isFoo = false;
                    object.notifyAll();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                /*bar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo.release();*/
                synchronized (object) {
                    if (isFoo) {
                        object.wait();
                    }
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    isFoo = true;
                    object.notifyAll();
                }
            }
        }
    }

}
