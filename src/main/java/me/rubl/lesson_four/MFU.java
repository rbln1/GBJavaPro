package me.rubl.lesson_four;

public class MFU {

    private final Object printLock = new Object();
    private final Object scanLock = new Object();
    private final Object copyLock = new Object();

    public void print() {
        System.out.printf("%s запросил возможность печати\n", Thread.currentThread().getName());

        synchronized (printLock) {
            System.out.printf("%s печатает\n", Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s закончил печать\n", Thread.currentThread().getName());
        }
    }

    public void scan() {
        System.out.printf("%s запросил возможность сканирования\n", Thread.currentThread().getName());

        synchronized (scanLock) {
            System.out.printf("%s сканирует\n", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s закончил сканировать\n", Thread.currentThread().getName());
        }
    }

    public void copy() {
        System.out.printf("%s запросил возможность ксерокопировать\n", Thread.currentThread().getName());

        synchronized (copyLock) {
            synchronized (printLock) {
                synchronized (scanLock) {
                    System.out.printf("%s ксерокопирует\n", Thread.currentThread().getName());
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%s закончил ксерокопировать\n", Thread.currentThread().getName());
                }
            }
        }
    }
}
