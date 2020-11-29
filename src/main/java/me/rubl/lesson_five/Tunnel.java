package me.rubl.lesson_five;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore capacity;

    private int size;

    public Tunnel(int size) {
        this.size = size;
        this.capacity = new Semaphore(size);
        this.length = 500;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {

        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                capacity.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                capacity.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
