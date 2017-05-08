package com.yzs.deadlock;
/**
 * 资源抢占发生死锁
 * @author YZS
 */
public class DeadLockTest1 {
	public static void main(String[] args) {

		// 定义两个资源
		Object resource1 = "面包";
		Object resource2 = "牛奶";
		// 第一个线程thread1，想先占有面包,再尝试占有牛奶
		Thread thread1 = new Thread() {
			public void run() {
				// resource1去占有面包
				synchronized (resource1) {
					// 成功持有面包
					System.out.println("Thread1:占有了  " + resource1);
					// 休眠一段时间
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					// thread2去占有牛奶,如果不能占有，thread1将会一直等待
					synchronized (resource2) {

						System.out.println("Thread1:占有了  " + resource2);

					}
				}
			}
		};

		// 第二个线程thread2，想要先占有牛奶.再占有面包
		Thread thread2 = new Thread() {
			public void run() {
				// thread2去占有牛奶
				synchronized (resource2) {
					// 成功占有牛奶
					System.out.println("Thread2:占有了  " + resource2);
					// 休眠一段时间
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
					// thread2去占有面包,如果不能占有，thread2将会一直等待
					synchronized (resource1) {
						System.out.println("Thread2:占有了  " + resource1);
					}
				}
			}
		};

		thread1.start();
		thread2.start();

	}
}
