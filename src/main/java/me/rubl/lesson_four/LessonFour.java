package me.rubl.lesson_four;

public class LessonFour {

    private static final int TIMES_COUNTER = 5;
    private static final Object monitor = new Object();
    private static char currentChar = 'A';

    public static void main(String[] args) {
        //taskOne();
        taskTwo();
    }

    private static void taskOne() {
        new Thread(() -> printChar('A', 'B')).start();
        new Thread(() -> printChar('B', 'C')).start();
        new Thread(() -> printChar('C', 'A')).start();
    }

    private static void taskTwo() {

        MFU mfu = new MFU();

        new Thread(mfu::copy).start();
        new Thread(mfu::copy).start();
        new Thread(mfu::print).start();
        new Thread(mfu::scan).start();
        new Thread(mfu::copy).start();
        new Thread(mfu::print).start();
        new Thread(mfu::print).start();
        new Thread(mfu::scan).start();
        new Thread(mfu::copy).start();
        new Thread(mfu::scan).start();
        new Thread(mfu::print).start();
    }

    private static void printChar(char startChar, char endChar) {
        synchronized (monitor) {
            for (int i = 0; i < TIMES_COUNTER; i++) {
                try {
                    while (currentChar != startChar) {
                        monitor.wait();
                    }
                    System.out.print(currentChar);
                    currentChar = endChar;
                    monitor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
