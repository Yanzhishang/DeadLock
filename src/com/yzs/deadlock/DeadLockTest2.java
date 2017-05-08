package com.yzs.deadlock;

class Test implements Runnable{
	
	public int num = 1;
	
	private boolean flag;
	public Test(boolean flag) {
		this.flag = flag;
	}
	
	@Override
	public void run() {
		
		if (flag) {
			while (true) {
				//同步嵌套
				synchronized(MyLock.locka){
					System.out.println(Thread.currentThread()
							.getName() + "     *****    if   下 的   Locka....");
					synchronized (MyLock.lockb) {
						
						System.out.println(Thread.currentThread()
								.getName() + "     *****    if   下 的   Lockb....");
						
					}
				}
			}
		}else {
			while(true){
				//同步嵌套
				synchronized(MyLock.lockb){
					System.out.println(Thread.currentThread()
							.getName() + "     *****    else 下 的   Lockb....");
					synchronized (MyLock.locka) {
						System.out.println(Thread.currentThread()
								.getName() + "     *****    else 下 的   Locka....");
						
					}
				}
			}
		}
	}
}


class MyLock{
	//new 两个锁对象
	public static final Object locka = new Object();
	public static final Object lockb = new Object();
	
}




public class DeadLockTest2 {

	public static void main(String[] args) {
			
		Test a = new Test(true);
		Test b = new Test(false);
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		
//		t1.start();
		t2.start();
	}

}
