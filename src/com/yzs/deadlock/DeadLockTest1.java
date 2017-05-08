package com.yzs.deadlock;

/**
 * ��Դ��ռ��������
 * 
 * @author YZS
 */
public class DeadLockTest1 {
	public static void main(String[] args) {
		/**
		 * �����̶߳�ռ����һ����Դ֮����Ҫ��ȥռ����һ����Դ����ֻ�ܵȴ��Է��ͷ���Դ֮�������ȥռ�С�����������ɶ����ڵȴ�״̬����Ͳ�����������
		 */
		// ��Դ:ţ�̺����
		Object resource1 = "���";
		Object resource2 = "ţ��";

		// ��һ���߳�thread1������ռ�����,�ٳ���ռ��ţ��
		Thread thread1 = new Thread() {
			public void run() {
				// resource1ȥռ�����
				synchronized (resource1) {
					// �ɹ��������
					System.out.println("Thread1:ռ����  " + resource1);
					try {
						// ����һ���£�����һ���߳����㹻��ʱ��ȥռ����һ����Դ
						Thread.sleep(50);
					} catch (Exception e) {

					}
					// thread2ȥռ��ţ��,�������ռ�У�thread1����һֱ�ȴ�
					synchronized (resource2) {
						System.out.println("Thread1:ռ����  " + resource2);
					}
				}
			}
		};

		// �ڶ����߳�thread2����Ҫ��ռ��ţ��.��ռ�����
		Thread thread2 = new Thread() {
			public void run() {
				// thread2ȥռ��ţ��
				synchronized (resource2) {
					// �ɹ�ռ��ţ��
					System.out.println("Thread2:ռ����  " + resource2);
					try {
						Thread.sleep(50);
					} catch (Exception e) {

					}
					// thread2ȥռ�����,�������ռ�У�thread2����һֱ�ȴ�
					synchronized (resource1) {
						System.out.println("Thread2:ռ����  " + resource1);
					}
				}
			}
		};

		thread1.start();
		thread2.start();

	}
}
