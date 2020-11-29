package me.rubl.lesson_five;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Car implements Runnable {

    private static int CARS_COUNT;

    static {
        CARS_COUNT = 1;
    }

    private Race race;
    private int speed;
    private CyclicBarrier prepareBarrier;
    private AtomicInteger placeCounter;
    private String name;
    private int place;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier prepareBarrier, AtomicInteger placeCounter) {
        this.race = race;
        this.speed = speed;
        this.prepareBarrier = prepareBarrier;
        this.placeCounter = placeCounter;
        this.name = "Участник #" + CARS_COUNT;
        CARS_COUNT++;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            prepareBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        place = placeCounter.incrementAndGet();

        if (place == 1) {
            System.out.println(this.name + " победил!");
        } else {
            System.out.println(this.name + " занял " + place + " место!");
        }
    }
}
