package com.yzs.deadlock;

/**
 * ͬ����Ƕ�ײ�������
 * @author YZS
 *
 */

class Ticket implements Runnable{
	
	public int num = 100;
	
	Object obj = new Object();
	
	public boolean flag;
	public Ticket(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		if (flag) {
			while (true) {
				synchronized(this){
					//ͬ���������ͬ������
					show();
				}
			}
		}else {
			while (true) {
				this.show();
			}
		}
	}

	public synchronized void show() {
		//ͬ��������ͬ�������
		synchronized (obj) {
			if (num > 0 ) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread()
						.getName() + "********" + num--);
			}
		}
	}
}



public class DeadLockTest1 {

	public static void main(String[] args) {
		
		Ticket a = new Ticket(true);
		Ticket b = new Ticket(false);
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		
		t1.start();
		t2.start();
	}
}
