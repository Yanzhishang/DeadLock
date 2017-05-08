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
				//ͬ��Ƕ��
				synchronized(MyLock.locka){
					System.out.println(Thread.currentThread()
							.getName() + "     *****    if   �� ��   Locka....");
					synchronized (MyLock.lockb) {
						
						System.out.println(Thread.currentThread()
								.getName() + "     *****    if   �� ��   Lockb....");
						
					}
				}
			}
		}else {
			while(true){
				//ͬ��Ƕ��
				synchronized(MyLock.lockb){
					System.out.println(Thread.currentThread()
							.getName() + "     *****    else �� ��   Lockb....");
					synchronized (MyLock.locka) {
						System.out.println(Thread.currentThread()
								.getName() + "     *****    else �� ��   Locka....");
						
					}
				}
			}
		}
	}
}


class MyLock{
	//new ����������
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
