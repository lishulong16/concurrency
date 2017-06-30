package thread.test;

/**
 * @Title: SynchronizedTest
 * @Description:对某个对象进行加锁
 * @Author lishulong
 * @Date 2017/6/30 15:11
 */
public class SynchronizedTest {

    private int count = 10;
    private static int mcount = 10;

    private Object object = new Object();

    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(() -> synchronizedTest.doSome()).start();
        new Thread(() -> synchronizedTest.doSome1()).start();

        new Thread(synchronizedTest::doSome).start();
    }

    /**
     * 互斥锁
     */
    public void doSome() {
        synchronized (object) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }
    }

    /**
     * 互斥锁 synchronized 锁定的是当前对象
     */
    public void doSome1() {
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count:" + count);
        }
    }

    /**
     * 互斥锁
     */
    public static synchronized void doSome2() {
        mcount--;
        System.out.println(Thread.currentThread().getName() + " count:" + mcount);
    }

    /**
     * 互斥锁
     */
    public static void doSome3() {
        synchronized (SynchronizedTest.class) {
            mcount--;
            System.out.println(Thread.currentThread().getName() + " count:" + mcount);
        }
    }
}
